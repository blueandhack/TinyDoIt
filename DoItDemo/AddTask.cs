using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using BLL;
using Model;

namespace DoItDemo
{
    public partial class AddTask : Form
    {
        string username;
        string title;
        string remark;
        string box;
        string beginDate;
        string endDate;
        int alarmTurn;
        string alarmTime;

        public AddTask()
        {
            InitializeComponent();
        }

        public AddTask(string UserName)
        {
            
            InitializeComponent();
            username = UserName;
        }

        TaskBLL tbl = new TaskBLL();


        //设置闹铃时间
        private void checkBoxAlarm_CheckedChanged(object sender, EventArgs e)
        {
            CheckBox check=(CheckBox)sender;
            if (check.Checked)
            {
                dateTimePickerAlarmDate.Enabled = true;
                dateTimePickerAlarmTime.Enabled = true;
            }
            else
            {
                dateTimePickerAlarmDate.Enabled = false;
                dateTimePickerAlarmTime.Enabled = false;
            }
        }

        private void AddTask_Load(object sender, EventArgs e)
        {
            comboBox1.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {

            title=textBox1.Text;
            remark = textBox2.Text ;
            box = comboBox1.SelectedItem.ToString();
            beginDate = dateTimePicker1.Value.ToString("yyyy-MM-dd") + " " + dateTimePicker2.Value.ToString("HH:mm:ss");
            endDate = dateTimePicker3.Value.ToString("yyyy-MM-dd") + " 00:00:00";
            if(checkBoxAlarm.Checked==true)
            {
                alarmTurn=1;
            }
            else
            {
                alarmTurn = 0;
            }
            alarmTime = dateTimePickerAlarmDate.Value.ToString("yyyy-MM-dd") + " " + dateTimePickerAlarmTime.Value.ToString("HH:mm:ss");

            if (tbl.addTask(username, title, remark, box, beginDate, endDate, alarmTurn, alarmTime))
            {
                MessageBox.Show("OK");
                this.Close();
            }
            else 
            {
                MessageBox.Show("出现异常");
            }


        }
    }
}
