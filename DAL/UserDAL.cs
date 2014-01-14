using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;
using System.Data;
using Model;


namespace DAL
{
    public class UserDAL
    {
        public UserDAL()
        {

        }

        SQLiteConnection conn;
        User newUser;

        #region 创建用户数据库文件
        public bool createUserDatabase()
        {
            conn = ConnectDB.OpenUser(conn);
            string sql = "create table User(Id integer PRIMARY KEY autoincrement,UserName varchar(8) not null,Password varchar(16) not null,Question varchar(20),Answer varchar(20))";
            SQLiteCommand createTable = new SQLiteCommand(sql, conn);
            createTable.ExecuteNonQuery();
            ConnectDB.CloseDB(conn);
            return true;
        }
        #endregion



        #region 检测用户登录时的账户和密码是否正确
        public bool checkUser(string userName, string password)
        {
            conn = ConnectDB.OpenUser(conn);
            string sql = "select * from User where UserName='" + userName + "' and Password='" + password + "'";
            SQLiteCommand cmd = new SQLiteCommand(sql, conn);
            SQLiteDataReader sdr = cmd.ExecuteReader();
            if (sdr.Read())
            {
                ConnectDB.CloseDB(conn);
                return true;
            }
            else
            {
                ConnectDB.CloseDB(conn);
                return false;
            }
        }
        #endregion

        public bool registerUser(string userName, string password, string question, string answer)
        {
            conn = ConnectDB.OpenUser(conn);
            string sql = "insert into User(username,password,question,answer) values ('" + userName + "','" + password + "','" + question + "','" + answer + "')";
            SQLiteCommand cmd = new SQLiteCommand(sql, conn);
            cmd.ExecuteNonQuery();
            ConnectDB.CloseDB(conn);
            return true;
        }
    }
}
