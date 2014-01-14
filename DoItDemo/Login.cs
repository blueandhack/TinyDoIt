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
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        UserBLL ubl = new UserBLL();
        TaskBLL tbl = new TaskBLL();
        User newUser;

        private void button1_Click(object sender, EventArgs e)
        {
            string username = textUserName.Text.ToString();
            string password = textPassword.Text.ToString();
            if (ubl.checkUserDatabase())
            {

                if (ubl.checkUser(username, password))
                {
                    if (tbl.checkUserTaskDatabase(username))
                    {
                        Main mainForm = new Main(username);
                        mainForm.Show();
                        this.Visible = false;
                    }
                    else
                    {
                        MessageBox.Show("出现数据库异常，请重试");
                    }
                }
                else
                {
                    MessageBox.Show("账号或密码有误");
                }
            }
            else 
            { 
                MessageBox.Show("正在创建数据库,请稍后重试"); 
            }

        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Register registerForm = new Register();
            registerForm.Show();
        }

        private void linkLabel2_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {

        }

        private void textPassword_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == 13)
            {
                buttonLogin.PerformClick();
            }
        }


    }
}
