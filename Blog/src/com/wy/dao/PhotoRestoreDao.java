/*    */package com.wy.dao;

/*    */
/*    *//*    */import java.sql.ResultSet;
/*    */
import java.sql.SQLException;
/*    */
import java.util.ArrayList;
/*    */
import java.util.List;

import com.wy.form.RestoreForm;
/*    */
import com.wy.tool.JDBConnection;

/*    */
/*    */public class PhotoRestoreDao
/*    */{
	/* 12 */private JDBConnection connection = null;

	/*    */
	/*    */public PhotoRestoreDao() {
		/* 15 */connection = new JDBConnection();
		/*    */}

	/*    */
	/*    */public boolean operationRestore(String operation, RestoreForm form) {
		/* 19 */boolean flag = false;
		/* 20 */String sql = "";
		/* 21 */if (operation.equals("���"))
			/* 22 */sql =
			/*    */
			/* 24 */"insert into tb_photo_restore values ('" + form.getArticleId() + "','" + form.getReAccount()
					+ "','" + form.getReTitle() + "','" + form.getReContent() + "')";
		/* 25 */if (operation.equals("ɾ��"))
			/* 26 */sql =
			/* 27 */"delete from tb_photo_restore where id='" + form.getId() + "'";
		/* 28 */if (connection.executeUpdate(sql)) {
			/* 29 */flag = true;
			/*    */}
		/* 31 */return flag;
		/*    */}

	/*    */
	/*    */public List queryRestore(Integer photoId) {
		/* 35 */List list = new ArrayList();
		/* 36 */String sql = "select * from tb_photo_restore where photoId='" + photoId +
		/* 37 */"' order by id desc";
		/* 38 */RestoreForm form = null;
		/* 39 */ResultSet rs = connection.executeQuery(sql);
		/*    */try {
			/* 41 */while (rs.next()) {
				/* 42 */form = new RestoreForm();
				/* 43 */form.setId(Integer.valueOf(rs.getInt(1)));
				/* 44 */form.setArticleId(Integer.valueOf(rs.getInt(2)));
				/* 45 */form.setReAccount(rs.getString(3));
				/* 46 */form.setReTitle(rs.getString(4));
				/* 47 */form.setReContent(rs.getString(5));
				/* 48 */list.add(form);
				/*    */}
			/*    */}
		/*    */catch (SQLException e) {
			/* 52 */e.printStackTrace();
			/*    */}
		/* 54 */return list;
		/*    */}
	/*    */
}

/*
 * Location: D:\workspace\javaee\Blog\WebContent\WEB-INF\classes Qualified Name:
 * com.wy.dao.PhotoRestoreDao Java Class Version: 7 (51.0) JD-Core Version:
 * 0.7.1
 */