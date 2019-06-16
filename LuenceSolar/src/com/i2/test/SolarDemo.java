package com.i2.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolarDemo {
	/**
	 * 创建
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void solaradd() throws SolrServerException, IOException {
//		1.创建solar服务地址
		String url ="http://localhost:8080/solr";
		HttpSolrServer server = new HttpSolrServer(url);
//		2.创建solar写入文档对象
		SolrInputDocument docint = new SolrInputDocument();
		docint.addField("id", "001");
		docint.addField("title", "phone");
//		3.将索引写入到索引库
		server.add(docint);
//		4.提交
		server.commit();
	}
	/**
	 * 删除索引
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void deletesolr() throws SolrServerException, IOException {
//		1.创建服务地址
		String url ="http://localhost:8080/solr";
		HttpSolrServer server = new HttpSolrServer(url);
//		2.根据条件删除
		server.deleteById("001");
//		3.提交
		server.commit();
	}

	/**
	 * 查询
	 * @throws SolrServerException 
	 */
	public void querysolr() throws SolrServerException {
//		1.创建查询对象
		SolrQuery query = new SolrQuery();
//		2.设置查询条件
		query.setQuery("*:*");
//		3.发起查询请求
		String url ="http://localhost:8080/solr";
		HttpSolrServer server = new HttpSolrServer(url);
		QueryResponse response = server.query(query);
//		4.处理查询结果
		SolrDocumentList results = response.getResults();
		System.out.println("total hits:\t"+results.getNumFound());//总条数
		for(SolrDocument doc:results) {
			System.out.println("id: \t"+doc.get("id"));
		}
	}
}
