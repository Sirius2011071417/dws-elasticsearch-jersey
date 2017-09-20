package cn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryBuilder;

import cn.dao.BaseDao;
import cn.domain.Page;
import cn.utils.ESClient;

public class PlanDao implements BaseDao {

	private String index;
	public PlanDao() {
		super();
	}
	public PlanDao(String index) {
		this.index = index;
	}
	@Override
	public Page list(QueryBuilder qb, int page, int page_size) {
		Page page_class = new Page();

		TransportClient client = ESClient.getClient();
		
		SearchResponse response = client.prepareSearch("plan")
				.setQuery(qb)
				.addSort("release_date", SortOrder.DESC)
				.addSort("create_time", SortOrder.DESC)
				.addSort("_score", SortOrder.DESC)
				.setFrom((page - 1) * page_size)
				.setSize(page_size)
				.get();
		String dict = client.prepareSearch("plan")
				.setQuery(qb)
				.addSort("release_date", SortOrder.DESC)
				.addSort("create_time", SortOrder.DESC)
				.addSort("_score", SortOrder.DESC)
				.setFrom((page - 1) * page_size)
				.setSize(page_size).toString();
		System.out.println(dict);
		SearchHits hits = response.getHits();
		long count = hits.getTotalHits();
		List<String> list = new ArrayList<String>();
		for (SearchHit hit : hits) {
			// Map<String, Object> sourceAsString = hit.getSourceAsMap();
			String sourceAsString = hit.getSourceAsString();
			if (sourceAsString != null) {
				list.add(sourceAsString);
			}
		}
		page_class.setList(list);
		page_class.setCount(count);
		return page_class;
	}

	@Override
	public String get(String uuid) {
		TransportClient client = ESClient.getClient();
		GetResponse response = client.prepareGet("plan", "plan", uuid).get();
		return response.getSourceAsString();
	}

	@Override
	public QueryBuilder title__contains(String title) {
		QueryBuilder qb = QueryBuilders.matchQuery("title", title)
				.analyzer("ik_max_word")
				.minimumShouldMatch("3<90%");;
		return qb;
	}
	
	@Override
	public QueryBuilder keyword__contains(String keyword) {
		QueryBuilder qb = QueryBuilders.multiMatchQuery(keyword, "title", "content")
				.analyzer("ik_max_word")
				.minimumShouldMatch("3<90%");
		return qb;
	}
	
	@Override
	public QueryBuilder release_date__lte(String lte) {
		QueryBuilder qb = QueryBuilders.rangeQuery("release_date").lte(lte);
		return qb;
	}
	@Override
	public QueryBuilder release_date__gte(String gte) {
		QueryBuilder qb = QueryBuilders.rangeQuery("release_date").gte(gte);
		return qb;
	}

	@Override
	public QueryBuilder all() {
		QueryBuilder qb = QueryBuilders.boolQuery().minimumShouldMatch(1);
		return qb;
	}
}
