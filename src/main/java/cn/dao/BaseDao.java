package cn.dao;

import org.elasticsearch.index.query.QueryBuilder;

import cn.domain.Page;

public interface BaseDao {

	Page list(QueryBuilder qb, int page, int page_size);

	String get(String uuid);

	QueryBuilder title__contains(String title);

	QueryBuilder keyword__contains(String keyword);

	QueryBuilder all();

	QueryBuilder release_date__lte(String lte);

	QueryBuilder release_date__gte(String gte);

}