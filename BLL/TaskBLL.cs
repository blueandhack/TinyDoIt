using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DAL;
using Model;
using System.IO;

namespace BLL
{
    public class TaskBLL
    {
        TaskDAL tdl = new TaskDAL();

        /// <summary>
        /// 检测是否存在此用户的任务数据库文件
        /// </summary>
        /// <param name="userName"></param>
        /// <returns></returns>
        public bool checkUserTaskDatabase(string userName)
        {
            if (File.Exists(Environment.CurrentDirectory + "\\User\\" + userName + ".db"))
            {
                return true;
            }
            else
            {
                if (tdl.createUserTaskDatabase(userName))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }


        public bool addTask(string username,string Title, string Remark, string Box, string BeginDate, string EndDate, int AlarmTurn, string AlarmTime)
        {
            if (tdl.addTask(username, Title, Remark, Box, BeginDate, EndDate, AlarmTurn, AlarmTime))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public List<Tasks> getAllTitle(string username)
        {
            return tdl.getAllTitle(username);
        }

        public Tasks getTaskById(string username, int Id)
        {
            return tdl.getTaskById(username, Id);
        }

        public bool getTaskAlarm(string username,string time)
        {
            if (tdl.getTaskAlarm(username, time))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
