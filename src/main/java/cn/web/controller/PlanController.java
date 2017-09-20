package cn.web.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import cn.dao.BaseDao;
import cn.dao.impl.PlanDao;
import cn.domain.Page;

@Path("api/plan")
public class PlanController {

	private BaseDao planDao;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response query(JSONObject query) {
		planDao = new PlanDao();
		int page;
		int page_size;
		try {
			page = query.getInt("page");
		} catch (JSONException e1) {
			page = 1;
		}
		try {
			page_size = query.getInt("page_size");
		} catch (JSONException e1) {
			page_size = 15;
		}
		// 解析query
		JSONObject query_json = null;
		try {
			query_json = query.getJSONObject("query");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		QueryBuilder qb = parse(query_json, planDao.all(), "and");

		Page page_class = planDao.list(qb, page, page_size);
		page_class.setPage(page);
		page_class.setPage_size(page_size);
		List<JSONObject> jsonObj = new ArrayList<JSONObject>();
		List<String> list = (List<String>) page_class.getList();
		for (String s : list) {
			try {
				jsonObj.add(new JSONObject(s));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsonArray = new JSONArray(jsonObj);
		JSONObject data = new JSONObject();
		try {
			data.put("page", page_class.getPage());
			data.put("page_size", page_class.getPage_size());
			data.put("results", jsonArray);
			data.put("count", page_class.getCount());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(data).build();
	}

	@POST
	@Path("/{uuid}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response get(@PathParam("uuid") String uuid, JSONObject query) {
		planDao = new PlanDao();
		String str = planDao.get(uuid);
		JSONObject data = null;
		try {
			data = new JSONObject();
			data.put("data", new JSONObject(str));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(data).build();
	}

	public QueryBuilder parse(JSONObject query, QueryBuilder qb, String operator) {
		Iterator iterator = query.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.equals("and") || key.equals("or") || key.equals("not")) {
				try {
					JSONArray value = query.getJSONArray(key);
					for(int i=0;i<value.length();i++) {
						JSONObject ele = value.getJSONObject(i);
						qb =  this.parse(ele, qb, key);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else {
				try {
					Method method = planDao.getClass().getMethod(key, String.class);
					if(operator.equals("and")) {
						qb = ((BoolQueryBuilder) qb).must((QueryBuilder) method.invoke(planDao, query.getString(key)));
					}else if(operator.equals("or")) {
						qb = ((BoolQueryBuilder) qb).should((QueryBuilder) method.invoke(planDao, query.getString(key)));
					}else if(operator.equals("not")) {
						qb = ((BoolQueryBuilder) qb).mustNot((QueryBuilder) method.invoke(planDao, query.getString(key)));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return qb;
	}
}
