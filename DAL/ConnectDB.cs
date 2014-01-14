using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;
using System.IO;

namespace DAL
{
    class ConnectDB
    {
        public ConnectDB() { }

        //连接用户表数据库
        public static SQLiteConnection OpenUser(SQLiteConnection conn)
        {
            if (!Directory.Exists(Environment.CurrentDirectory + "\\User"))
            { 
                Directory.CreateDirectory(Environment.CurrentDirectory + "\\User");
            }
            string connectString = @"Data Source=" + Environment.CurrentDirectory + "\\User\\User.db;Pooling=true;FailIfMissing=false";
            conn = new SQLiteConnection(connectString); //新建一个连接
            conn.Open();  //打开连接
            return conn;
        }

        //连接用户任务数据库
        public static SQLiteConnection OpenTask(SQLiteConnection conn, string DatabaseName)
        {
            if (!Directory.Exists(Environment.CurrentDirectory + "\\User"))
            {
                Directory.CreateDirectory(Environment.CurrentDirectory + "\\User");
            }
            string connectString = @"Data Source=" + Environment.CurrentDirectory + "\\User\\" + DatabaseName + ".db;Pooling=true;FailIfMissing=false";
            conn = new SQLiteConnection(connectString); //新建一个连接
            conn.Open();  //打开连接
            return conn;
        }


        public static void CloseDB(SQLiteConnection conn)
        {
            conn.Close();
        }

    }
}
