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
    public class TaskDAL
    {
        public TaskDAL()
        { 

        }

        SQLiteConnection conn = null;
        SQLiteCommand cmd = null;
        ConnectDB cdb = new ConnectDB();

        List<Tasks> listData = new List<Tasks>();
        Tasks task;

        #region 创建用户任务数据库文件
        /// <summary>
        /// 创建用户任务数据库文件
        /// </summary>
        /// <param name="userNameDatabase"></param>
        /// <returns></returns>
        public bool createUserTaskDatabase(string userNameDatabase)
        {
            conn = ConnectDB.OpenTask(conn, userNameDatabase);
            string sql = "create table Task(Id integer PRIMARY KEY autoincrement, Title varchar(20), Remark varchar(30), Box varchar(10), BeginDate datetime, EndDate datetime, AlarmTurn integer, AlarmTime datetime)";
            SQLiteCommand createTable = new SQLiteCommand(sql, conn);
            createTable.ExecuteNonQuery();
            ConnectDB.CloseDB(conn);
            return true;
        }
        #endregion

        public bool addTask(string username,string Title, string Remark, string Box, string BeginDate, string EndDate, int AlarmTurn, string AlarmTime)
        {
            conn = ConnectDB.OpenTask(conn, username);
            string sql = "insert into Task(Title , Remark , Box , BeginDate , EndDate , AlarmTurn, AlarmTime) values('" + Title + "', '" + Remark + "','" + Box + "','" + BeginDate + "','" + EndDate + "'," + AlarmTurn + ",'" + AlarmTime + "')";
            SQLiteCommand insertTask = new SQLiteCommand(sql, conn);
            insertTask.ExecuteNonQuery();
            ConnectDB.CloseDB(conn);
            return true;
        }

        public List<Tasks> getAllTitle(string username)
        {
            conn = ConnectDB.OpenTask(conn, username);
            string sql = "select Id,Title from Task";
            SQLiteCommand allTask = new SQLiteCommand(sql, conn);
            SQLiteDataReader read = allTask.ExecuteReader();
            while (read.Read())
            {
                listData.Add(new Tasks(int.Parse(read.GetValue(0).ToString()), read.GetValue(1).ToString()));
            }
            ConnectDB.CloseDB(conn);
            return listData;
        }

        /// <summary>
        /// 通过ID获取任务详情
        /// </summary>
        /// <param name="username"></param>
        /// <param name="id"></param>
        /// <returns></returns>
        public Tasks getTaskById(string username,int id)
        {
            conn = ConnectDB.OpenTask(conn, username);
            string sql = "select * from Task where Id="+id;
            SQLiteCommand getTask = new SQLiteCommand(sql, conn);
            SQLiteDataReader read = getTask.ExecuteReader();
            while (read.Read())
            {
                task = new Tasks(read.GetValue(1).ToString(), read.GetValue(2).ToString(), read.GetValue(3).ToString(), read.GetValue(4).ToString(), read.GetValue(5).ToString(), int.Parse(read.GetValue(6).ToString()), read.GetValue(7).ToString());
            }
            return task;
        }

        public bool getTaskAlarm(string username,string time)
        {
            conn = ConnectDB.OpenTask(conn, username);
            string sql = "select AlarmTime,AlarmTurn from Task where AlarmTurn=1 and AlarmTime='"+time+"'";
            SQLiteCommand getAlarm = new SQLiteCommand(sql, conn);
            SQLiteDataReader read = getAlarm.ExecuteReader();
            if (read.Read())
            {
                return true;
            }
            ConnectDB.CloseDB(conn);
            return false; 
        }
    }
}
