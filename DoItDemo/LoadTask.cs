using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Model;
using BLL;

namespace DoItDemo
{
    public partial class LoadTask : Form
    {
        Tasks newTask;

        public LoadTask()
        {
            InitializeComponent();
        }

        public LoadTask(Tasks task)
        {
            InitializeComponent();
            newTask = task;
        }

        private void LoadTask_Load(object sender, EventArgs e)
        {
            textBox1.Text = newTask.Title.ToString();
            textBox2.Text = newTask.Remark.ToString();
            comboBox1.Text = newTask.Box.ToString();
            dateTimePicker1.Value = DateTime.Now;

            if (newTask.AlarmTurn != 0)
            {
                checkBoxAlarm.Checked = true;
            }
            else 
            {
                checkBoxAlarm.Checked = false;
            }
        }
    }
}
