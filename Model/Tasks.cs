using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class Tasks
    {
        private int id;

        public int Id
        {
            get { return id; }
            set { id = value; }
        }


        private string title;

        public string Title
        {
            get { return title; }
            set { title = value; }
        }
        
        private string remark;

        public string Remark
        {
            get { return remark; }
            set { remark = value; }
        }

        private string box;

        public string Box
        {
            get { return box; }
            set { box = value; }
        }
        private string beginDate;

        public string BeginDate
        {
            get { return beginDate; }
            set { beginDate = value; }
        }
        private string endDate;

        public string EndDate
        {
            get { return endDate; }
            set { endDate = value; }
        }
        private int alarmTurn;

        public int AlarmTurn
        {
            get { return alarmTurn; }
            set { alarmTurn = value; }
        }
        private string alarmTime;

        public string AlarmTime
        {
            get { return alarmTime; }
            set { alarmTime = value; }
        }
        //private string circumstances;

        public Tasks(string Title, string Remark, string Box, string BeginDate, string EndDate, int AlarmTurn, string AlarmTime)
        {
            title = Title;
            remark = Remark;
            box = Box;
            beginDate = BeginDate;
            endDate = EndDate;
            alarmTurn = AlarmTurn;
            alarmTime = AlarmTime;
        }

        public Tasks(int Id,string Title)
        {
            title = Title;
            id = Id;
        }

    }
}
