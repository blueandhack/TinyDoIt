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
using System.Text.RegularExpressions;

namespace DoItDemo
{
    public partial class Main : Form
    {
        string username;
        BindingSource bs;
        TaskBLL tbl = new TaskBLL();
        List<Tasks> listData = new List<Tasks>();

        public Main()
        {
            InitializeComponent();
        }

        public Main(string UserName)
        {
            InitializeComponent();
            username = UserName;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AddTask addTaskForm = new AddTask(username);
            addTaskForm.Show();

        }

        private void Main_Load(object sender, EventArgs e)
        {

            listData = tbl.getAllTitle(username);
            bs = new BindingSource();
            bs.DataSource = listData;

            listBox1.DataSource = bs;
            listBox1.DisplayMember = "title";
            listBox1.ValueMember = "id";


        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void listBox1_Click(object sender, EventArgs e)
        {

        }

        private void listBox1_DoubleClick(object sender, EventArgs e)
        {
            string NO = listBox1.SelectedValue.ToString();
            Regex reg = new Regex(@"^\d+$");
            if (reg.IsMatch(NO))
            {
                int id = int.Parse(NO);
                Tasks task = tbl.getTaskById(username, id);
                LoadTask LTask = new LoadTask(task);
                LTask.Show();
                //button1.Text = listBox1.SelectedValue.ToString();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {

        }

        private void Main_FormClosing(object sender, FormClosingEventArgs e)
        {
            e.Cancel = true;
            this.WindowState = FormWindowState.Minimized;
            this.ShowInTaskbar = false; 
        }

        private void notifyIcon1_DoubleClick(object sender, EventArgs e)
        {
            //if (this.WindowState == FormWindowState.Minimized)
            //{
            //    this.Show();
            //    this.WindowState = FormWindowState.Normal;
            //    this.ShowInTaskbar = true;
            //}

            if (this.WindowState == FormWindowState.Minimized)   //判断窗体是不是最小化
                this.WindowState = FormWindowState.Normal;   //如果是最小化就把窗体状态改为默认大小
            this.Activate();  //激活窗体并赋予焦点，这句话可以不写
            this.ShowInTaskbar = true;   //在Windows任务栏中显示窗体。
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            string nowTime = System.DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            if (tbl.getTaskAlarm(username, nowTime))
            {
                MessageBox.Show("我是闹钟");
            }

        }
    }
}
