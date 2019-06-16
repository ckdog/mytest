package com.i2.test;

import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.i2.pojo.Book;

public class TestDemo {

	private Logger log = Logger.getLogger(TestDemo.class);
//	@Test
	public List<Book> test001() {
		Connection conn=null;
		ResultSet result = null;
		PreparedStatement stat = null;
		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.CHINA);
		String driver = bundle.getString("jdbc.driver");
//		System.out.println(driver);
		String url = bundle.getString("jdbc.url");
		String user = bundle.getString("jdbc.username");
		String password = bundle.getString("jdbc.password");
		List<Book> list = new ArrayList<Book>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			String sql ="select * from book ";
			stat = conn.prepareStatement(sql);
			ResultSet executeQuery = stat.executeQuery();
			while(executeQuery.next()) {
				Book book = new Book();
				book.setId(String.valueOf(executeQuery.getInt("id")));
				book.setPrice(String.valueOf(executeQuery.getFloat("price")));
				book.setName(executeQuery.getString("name"));
				book.setDesc(executeQuery.getString("description"));
				book.setPic(executeQuery.getString("pic"));
				System.out.println(book.getName());
				list.add(book);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 创建索引库
	 * @throws IOException
	 */
	@Test
	public void lucenentest() throws IOException {
//		1.采集数据
		List<Book> list = test001();
//		2.创建document对象
		List<Document> doclist = new ArrayList<Document>();
		for(Book book : list) {
			Document doc = new Document();
			doc.add(new org.apache.lucene.document.TextField("id",book.getId(),Store.YES));
			doc.add(new org.apache.lucene.document.TextField("name",book.getName(),Store.YES));
			doc.add(new org.apache.lucene.document.TextField("pic",book.getPic(),Store.YES));
			doc.add(new org.apache.lucene.document.TextField("desc",book.getDesc(),Store.YES));
			doc.add(new org.apache.lucene.document.TextField("price",book.getPrice(),Store.YES));
			doclist.add(doc);
		}
//		3.创建分词器
//		Analyzer analyzer = new StandardAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
//		4.创建indexwriterconfig对象配置
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
//		5.创建directory对象
		Directory dir = FSDirectory.open(new File("D:/develop/lucenedocindex"));
//		6.创建indexwriter对象
		IndexWriter writer = new IndexWriter(dir, config);
//		7.吧对象写入的document对象中
		int i =0;
		for(Document docu:doclist) {
			System.out.println("===== \t"+i++);
			writer.addDocument(docu);
		}
//		8.释放资源
		writer.close();
//		log.info("======end lucene=======");
		
	}
	
	/**
	 * 分词查询
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Test
	public void testquery() throws ParseException, IOException {
	
//		1.创建query对象
//		Analyzer analyzer = new StandardAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
			//使用解析器设置解析参数
		QueryParser query = new QueryParser("desc", analyzer);
		Query parse = query.parse("java");
//		2.创建dirctory对象
		Directory dir = FSDirectory.open(new File("D:/develop/lucenedocindex"));
//		3.创建indexReader索引读取对象
		IndexReader reader = DirectoryReader.open(dir);
//		4.创建indexSearch索引搜索对象
		IndexSearcher search = new IndexSearcher(reader);
//		5.使用索引搜索对象搜索索引，返回结果topdocs
		TopDocs tops = search.search(parse, 10);
//		6.解析结果
		System.out.println(tops.totalHits+"条数据");
		ScoreDoc[] scoreDocs = tops.scoreDocs;
		for(ScoreDoc score:scoreDocs) {
			//获取文档
			int doc = score.doc;
			Document document = search.doc(doc);
			System.out.println("==========");
			System.out.print("id : "+document.get("id")+"\t");
			System.out.print("name : "+document.get("name")+"\t");
			System.out.print("price : "+document.get("price")+"\t");
			System.out.print("pic : "+document.get("pic")+"\t");
		}
//		7.释放资源
		reader.close();
		
	}
	/**
	 * 根据term删除索引
	 * @throws IOException 
	 */
	@Test
	public void deleteIndex() throws IOException {
//		1.创建directory
		Directory  dir = FSDirectory.open(new File("D:/develop/lucenedocindex"));
//		2.创建indexwriterconfig
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, null);
//		3.创建indexwriter
		IndexWriter writer = new IndexWriter(dir, config);
		
//		4.根据term删除索引
		writer.deleteDocuments(new Term("desc", "java"));
//		5.释放资源
		writer.close();
	}
	/**
	 * 修改索引
	 */
	@Test
	public void updateindex() throws IOException {
//		1.创建directory
		Directory  dir = FSDirectory.open(new File("D:/develop/lucenedocindex"));
//		2.创建indexwriterconfig
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, null);
//		3.创建indexwriter
		IndexWriter writer = new IndexWriter(dir, config);
		
//		4.根据term修改索引
		Document doc = new Document();
		doc.add(new org.apache.lucene.document.TextField("name", "lucene test",	Store.YES));
		writer.updateDocument(new Term("name","lucene"), doc);
//		5.释放资源
		writer.close();
	}
}
