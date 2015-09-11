using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DAL;
using System.IO;

namespace BLL
{
    public class UserBLL
    {
        public UserBLL()
        {

        }

        UserDAL udl = new UserDAL();


        public bool checkUserDatabase()
        {
            if (File.Exists(Environment.CurrentDirectory + "\\User\\User.db"))
            {
                return true;
            }
            else
            {
                if (udl.createUserDatabase())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }



        public bool checkUser(string userName, string password)
        {
            if (udl.checkUser(userName, password))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public bool registerUser(string userName,string password,string question,string answer)
        {
            if (udl.registerUser(userName, password, question, answer))
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
