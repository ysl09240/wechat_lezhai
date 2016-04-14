/**
 * 方法名：testgetConnection</br>
 * 详述：测试是否连接</br>
 * @author SongLin.Yang
 * @data 2016-04-13 14:20
 */

import java.sql.SQLException;

import org.junit.Test;

import com.slin.weixin.Util.DBUtility;

public class TestDBUtility {

    /**
     *
     * 开发人员：souvc </br>
     * 创建时间：2015-10-5  </br>
     * @throws SQLException
     * @throws
     */
    @Test
    public void testgetConnection() throws SQLException {
        DBUtility db = new DBUtility();
        System.out.println(db.getConnection());
    }


}
