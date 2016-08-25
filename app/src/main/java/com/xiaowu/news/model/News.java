package com.xiaowu.news.model;
/**
 * 新闻
 * @author caishijian
 *
 */
public class News {
	private int nid; //新闻id
	private int cid; //分类id
	private String title; //新闻标题
	private String digest; //新闻摘要
	private String body; //新闻正文
	private String source; //新闻来源
	private int commentCount; //评论数
	private String ptime; //发布时间
	private String imgSrc; //图片地址
	private boolean deleted; //是否已删除

	public News() {
	}

	public News(int nid, int cid, String title, String digest, String body,
			String source, int commentCount, String ptime, String imgSrc,
			boolean deleted) {
		this.nid = nid;
		this.cid = cid;
		this.title = title;
		this.digest = digest;
		this.body = body;
		this.source = source;
		this.commentCount = commentCount;
		this.ptime = ptime;
		this.imgSrc = imgSrc;
		this.deleted = deleted;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "News [nid=" + nid + ", cid=" + cid + ", title=" + title
				+ ", digest=" + digest + ", body=" + body + ", source="
				+ source + ", commentCount=" + commentCount + ", ptime="
				+ ptime + ", imgSrc=" + imgSrc + ", deleted=" + deleted + "]";
	}

}
