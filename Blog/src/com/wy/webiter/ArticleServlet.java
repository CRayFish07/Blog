/*     */package com.wy.webiter;

/*     */
/*     *//*     */import java.io.IOException;
/*     */
import java.io.PrintWriter;

/*     */
import javax.servlet.RequestDispatcher;
/*     */
import javax.servlet.ServletException;
/*     */
import javax.servlet.http.HttpServlet;
/*     */
import javax.servlet.http.HttpServletRequest;
/*     */
import javax.servlet.http.HttpServletResponse;

import com.wy.dao.ArticleDao;
/*     */
import com.wy.dao.ArticleTypeDao;
/*     */
import com.wy.dao.RestoreDao;
/*     */
import com.wy.form.ArticleForm;
/*     */
import com.wy.form.ArticleTypeForm;
/*     */
import com.wy.form.RestoreForm;
/*     */
import com.wy.tool.Chinese;

/*     */
/*     */
/*     */public class ArticleServlet
/*     */extends HttpServlet
/*     */{
	/* 22 */private ArticleDao articleDao = null;
	/*     */
	/* 24 */private ArticleTypeDao articleTypeDao = null;
	/*     */
	/* 26 */private RestoreDao restoreDao = null;

	/*     */
	/*     */public ArticleServlet() {
	}

	/*     */
	/*     */@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 31 */int method = Integer.parseInt(request.getParameter("method"));
		/* 32 */if (method == 0) {
			/* 33 */addArticleType(request, response);
			/*     */}
		/* 35 */if (method == 1) {
			/* 36 */deleteArticleType(request, response);
			/*     */}
		/* 38 */if (method == 2) {
			/* 39 */addArticle(request, response);
			/*     */}
		/* 41 */if (method == 3) {
			/* 42 */deleteArticle(request, response);
			/*     */}
		/* 44 */if (method == 4) {
			/* 45 */updateArticle(request, response);
			/*     */}
		/* 47 */if (method == 5) {
			/* 48 */headAddNumberArticle(request, response);
			/*     */}
		/* 50 */if (method == 6) {
			/* 51 */deleteRestore(request, response);
			/*     */}
		/* 53 */if (method == 7) {
			/* 54 */HeadAddRestore(request, response);
			/*     */}
		/*     */}

	/*     */
	/*     */public void HeadAddRestore(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	/*     */{
		/* 60 */response.setContentType("text/html;charset=GBK");
		/* 61 */PrintWriter out = response.getWriter();
		/* 62 */restoreDao = new RestoreDao();
		/* 63 */RestoreForm restoreForm = new RestoreForm();
		/* 64 */restoreForm.setArticleId(Integer.valueOf(request
		/* 65 */.getParameter("articleId")));
		/* 66 */restoreForm.setReAccount(request.getParameter("accountId"));
		/* 67 */restoreForm.setReTitle(Chinese.toChinese(request
		/* 68 */.getParameter("reTitle")));
		/* 69 */restoreForm.setReContent(Chinese.toChinese(request
		/* 70 */.getParameter("reContent")));
		/* 71 */if (restoreDao.operationRestore("���", restoreForm)) {
			out.print("��ӻظ���Ϣ�ɹ��������²�ѯ��");
			/* 72 */out.print("<script language=javascript>window.location.href='head_ArticleForm.jsp?id=" +
			/* 73 */request.getParameter("articleId") + "';</script>");
			/*     */} else {
			out.print("��ӻظ���Ϣʧ�ܣ�");
			/* 75 */out.print("<script language=javascript>history.go(-1);</script>");
			/*     */}
		/*     */}

	/*     */
	/*     */public void deleteRestore(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 82 */response.setContentType("text/html;charset=GBK");
		/* 83 */PrintWriter out = response.getWriter();
		/* 84 */restoreDao = new RestoreDao();
		/* 85 */RestoreForm restoreForm = new RestoreForm();
		/* 86 */restoreForm.setId(Integer.valueOf(request.getParameter("id")));
		/*     */
		/* 88 */if (restoreDao.operationRestore("ɾ��", restoreForm)) {
			/* 89 */out
					.print("<script language=javascript>alert('ɾ���ظ��ɹ��������²�ѯ��');window.location.href='back_RestoreSelect.jsp?id="
							+
							/* 90 */request.getParameter("idd") + "';</script>");
			/*     */} else {
			/* 92 */out.print("<script language=javascript>alert('ɾ���ظ�ʧ�ܣ�');history.go(-1);</script>");
			/*     */}
		/*     */}

	/*     */
	/*     */
	/*     */public void headAddNumberArticle(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 100 */ArticleForm articleForm = new ArticleForm();
		/* 101 */articleDao = new ArticleDao();
		/* 102 */articleForm.setId(Integer.valueOf(request.getParameter("id")));
		/* 103 */articleDao.operationArticle("����", articleForm);
		/* 104 */request.setAttribute("form", articleDao.queryArticleForm(
		/* 105 */Integer.valueOf(request.getParameter("id"))));
		/* 106 */RequestDispatcher requestDispatcher = request
		/* 107 */.getRequestDispatcher("head_ArticleForm.jsp");
		/* 108 */requestDispatcher.forward(request, response);
		/*     */}

	/*     */
	/*     */public void updateArticle(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 114 */response.setContentType("text/html;charset=GBK");
		/* 115 */PrintWriter out = response.getWriter();
		/* 116 */ArticleForm articleForm = new ArticleForm();
		/* 117 */articleForm.setId(Integer.valueOf(request.getParameter("id")));
		/* 118 */articleForm.setTypeId(Integer.valueOf(request.getParameter("typeId")));
		/* 119 */articleForm.setTitle(Chinese.toChinese(request.getParameter("title")));
		/* 120 */articleForm.setContent(Chinese.toChinese(request
		/* 121 */.getParameter("content")));
		/* 122 */articleDao = new ArticleDao();
		/* 123 */if (articleDao.operationArticle("�޸�", articleForm)) {
			/* 124 */out
					.print("<script language=javascript>alert('�޸����³ɹ��������²�ѯ��');window.location.href='back_ArticleSelect.jsp';</script>");
			/*     */} else {
			/* 126 */out.print("<script language=javascript>alert('�޸�����ʧ�ܣ�');history.go(-1);</script>");
			/*     */}
		/*     */}

	/*     */
	/*     */public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 133 */response.setContentType("text/html;charset=GBK");
		/* 134 */PrintWriter out = response.getWriter();
		/* 135 */ArticleForm articleForm = new ArticleForm();
		/* 136 */articleForm.setId(Integer.valueOf(request.getParameter("id")));
		/* 137 */articleDao = new ArticleDao();
		/* 138 */if (articleDao.operationArticle("ɾ��", articleForm)) {
			/* 139 */out
					.print("<script language=javascript>alert('ɾ�����³ɹ��������²�ѯ��');window.location.href='back_ArticleSelect.jsp';</script>");
			/*     */} else {
			/* 141 */out.print("<script language=javascript>alert('ɾ������ʧ�ܣ�');history.go(-1);</script>");
			/*     */}
		/*     */}

	/*     */
	/*     */public void addArticle(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 148 */ArticleForm articleForm = new ArticleForm();
		/* 149 */articleForm.setTypeId(Integer.valueOf(request.getParameter("typeId")));
		/* 150 */articleForm.setTitle(Chinese.toChinese(request.getParameter("title")));
		/* 151 */articleForm.setNumber(Integer.valueOf(request.getParameter("number")));
		/* 152 */articleForm.setContent(Chinese.toChinese(request
		/* 153 */.getParameter("content")));
		/* 154 */articleForm
		/* 155 */.setPhTime(Chinese.toChinese(request.getParameter("phTime")));
		/* 156 */articleDao = new ArticleDao();
		/* 157 */String result = "�������ʧ�ܣ�";
		/* 158 */if (articleDao.operationArticle("���", articleForm)) {
			/* 159 */result = "������ӳɹ���";
			/*     */}
		/* 161 */request.setAttribute("result", result);
		/* 162 */RequestDispatcher requestDispatcher = request
		/* 163 */.getRequestDispatcher("back_ArticleAdd.jsp");
		/* 164 */requestDispatcher.forward(request, response);
		/*     */}

	/*     */
	/*     */public void deleteArticleType(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 170 */response.setContentType("text/html;charset=GBK");
		/* 171 */PrintWriter out = response.getWriter();
		/* 172 */ArticleTypeForm ArticleTypeForm = new ArticleTypeForm();
		/* 173 */ArticleTypeForm.setId(Integer.valueOf(request.getParameter("id")));
		/* 174 */articleTypeDao = new ArticleTypeDao();
		/* 175 */if (articleTypeDao.operationArticleType("ɾ��", ArticleTypeForm)) {
			/* 176 */out
					.print("<script language=javascript>alert('ɾ���������ɹ��������²�ѯ��');window.location.href='back_ArticleTypeSelect.jsp';</script>");
			/*     */} else {
			/* 178 */out.print("<script language=javascript>alert('����Ҫ��������ڵ�����ɾ��,�ſ�ɾ�������');history.go(-1);</script>");
			/*     */}
		/*     */}

	/*     */
	/*     */
	/*     */public void addArticleType(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 186 */response.setContentType("text/html;charset=GBK");
		/* 187 */PrintWriter out = response.getWriter();
		/* 188 */ArticleTypeForm ArticleTypeForm = new ArticleTypeForm();
		/* 189 */ArticleTypeForm.setTypeName(Chinese.toChinese(request
		/* 190 */.getParameter("typeName")));
		/* 191 */ArticleTypeForm.setDescription(Chinese.toChinese(request
		/* 192 */.getParameter("description")));
		/* 193 */articleTypeDao = new ArticleTypeDao();
		/* 194 */if (articleTypeDao.operationArticleType("���", ArticleTypeForm)) {
			/* 195 */out
					.print("<script language=javascript>alert('����������ɹ��������²�ѯ��');window.location.href='back_ArticleTypeSelect.jsp';</script>");
			/*     */} else {
			/* 197 */out.print("<script language=javascript>alert('����������ʧ�ܣ�');history.go(-1);</script>");
			/*     */}
		/*     */}

	/*     */
	/*     */
	/*     */@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	/*     */throws ServletException, IOException
	/*     */{
		/* 205 */doGet(request, response);
		/*     */}
	/*     */
}

/*
 * Location: D:\workspace\javaee\Blog\WebContent\WEB-INF\classes Qualified Name:
 * com.wy.webiter.ArticleServlet Java Class Version: 7 (51.0) JD-Core Version:
 * 0.7.1
 */