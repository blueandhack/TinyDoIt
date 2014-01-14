using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class User
    {
        private string userName;

        public string UserName
        {
            get { return userName; }
            set { userName = value; }
        }
        private string password;

        public string Password
        {
            get { return password; }
            set { password = value; }
        }

        private string question;

        public string Question
        {
            get { return question; }
            set { question = value; }
        }

        private string answer;

        public string Answer
        {
            get { return answer; }
            set { answer = value; }
        }

        public User(string UserName ,string Password,string Question,string Answer)
        {
            userName = UserName;
            password = Password;
            question = Question;
            answer = Answer;
        }

        public User(string UserName, string Password)
        {
            userName = UserName;
            password = Password;
        }

        
    }
}
