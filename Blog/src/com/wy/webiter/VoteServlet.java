/*    */package com.wy.webiter;

/*    */
/*    *//*    */import java.io.IOException;
/*    */
import java.io.PrintWriter;

/*    */
import javax.servlet.ServletException;
/*    */
import javax.servlet.http.HttpServlet;
/*    */
import javax.servlet.http.HttpServletRequest;
/*    */
import javax.servlet.http.HttpServletResponse;

import com.wy.dao.VoteDao;
/*    */
import com.wy.form.VoteForm;
/*    */
import com.wy.tool.Chinese;

/*    */
/*    */
/*    */public class VoteServlet
/*    */extends HttpServlet
/*    */{
	/* 17 */private VoteDao voteDao = null;
	/*    */private int method;

	/*    */
	/*    */public VoteServlet() {
	}

	/*    */
	/*    */@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	/*    */{
		/* 24 */method = Integer.parseInt(request.getParameter("method"));
		/* 25 */if (method == 0) {
			/* 26 */addVote(request, response);
			/*    */}
		/* 28 */if (method == 1) {
			/* 29 */deleteVote(request, response);
			/*    */}
		/*    */}

	/*    */
	/*    */public void deleteVote(HttpServletRequest request, HttpServletResponse response)
	/*    */throws ServletException, IOException
	/*    */{
		/* 36 */response.setContentType("text/html;charset=GBK");
		/* 37 */PrintWriter out = response.getWriter();
		/* 38 */VoteForm voteForm = new VoteForm();
		/* 39 */voteDao = new VoteDao();
		/* 40 */voteForm.setId(Integer.valueOf(request.getParameter("id")));
		/* 41 */if (voteDao.operationVote("ɾ��", voteForm)) {
			/* 42 */out
					.print("<script language=javascript>alert('ɾ�������Գɹ��������½��в�ѯ��');window.location.href='back_VoteSelect.jsp';</script>");
			/*    */} else {
			/* 44 */out.print("<script language=javascript>alert('ɾ��������ʧ�ܣ�');history.go(-1);</script>");
			/*    */}
		/*    */}

	/*    */
	/*    */public void addVote(HttpServletRequest request, HttpServletResponse response)
	/*    */throws ServletException, IOException
	/*    */{
		/* 51 */VoteForm voteForm = new VoteForm();
		/* 52 */voteDao = new VoteDao();
		/* 53 */voteForm.setVoteName(Chinese.toChinese(request.getParameter("voteName")));
		/* 54 */voteForm.setVoteTime(Chinese.toChinese(request.getParameter("voteTime")));
		/* 55 */voteForm.setVoteContent(Chinese.toChinese(request
		/* 56 */.getParameter("voteContent")));
		/* 57 */response.setContentType("text/html;charset=GBK");
		/* 58 */PrintWriter out = response.getWriter();
		/* 59 */if (voteDao.operationVote("���", voteForm))
		/*    */{
			/* 61 */out
					.print("<script language=javascript>alert('�������Գɹ���');window.location.href='head_VoteAdd.jsp';</script>");
			/*    */} else {
			/* 63 */out
					.print("<script language=javascript>alert('��������ʧ�ܣ������·�����');window.location.href='head_VoteAdd.jsp';</script>");
			/*    */}
		/*    */}

	/*    */
	/*    */
	/*    */@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	/*    */throws ServletException, IOException
	/*    */{
		/* 71 */doGet(request, response);
		/*    */}
	/*    */
}

/*
 * Location: D:\workspace\javaee\Blog\WebContent\WEB-INF\classes Qualified Name:
 * com.wy.webiter.VoteServlet Java Class Version: 7 (51.0) JD-Core Version:
 * 0.7.1
 */