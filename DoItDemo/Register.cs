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
    public partial class Register : Form
    {

        public Register()
        {
            InitializeComponent();
        }

        UserBLL ubl = new UserBLL();
        TaskBLL tbl = new TaskBLL();
        string userName;
        string password;
        string question;
        string answer;

        private void button1_Click(object sender, EventArgs e)
        {
            userName = textBox1.Text;
            password = textBox2.Text;
            question = textBox3.Text;
            answer = textBox4.Text;
            if (ubl.checkUserDatabase() == true)
            {
                if (ubl.checkUser(userName, password) == false)
                {
                    if (ubl.registerUser(userName, password, question, answer))
                    {
                        tbl.checkUserTaskDatabase(userName);
                        MessageBox.Show("注册成功");
                    }
                    else
                    {
                        MessageBox.Show("注册失败");
                    }
                }
                else 
                {
                    MessageBox.Show("不可注册");
                }
            }

        }
        
    }
}
