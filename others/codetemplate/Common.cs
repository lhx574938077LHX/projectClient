using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Com.IceFox.Utils
{
    public class Common
    {
        /// <summary>
        /// 首字母大写
        /// </summary>
        /// <param name="v">英文字符串</param>
        /// <param name="atl">后续字母是否转换成小写</param>
        /// <returns></returns>
        public static string InitialToOpper(string v,bool atl)
        {
          if(string.IsNullOrEmpty(v)) return v;
          
          return v.Substring(0, 1).ToUpper() + (v.Length>1?(atl?v.Substring(1).ToLower():v.Substring(1)):"");
        }
        
        /// <summary>
        /// 首字母小写
        /// </summary>
        /// <param name="v">英文字符串</param>
        /// <param name="atl">后续字母是否转换成小写</param>
        /// <returns></returns>
        public static string InitialToLower(string v,bool atl)
        {
          if(string.IsNullOrEmpty(v)) return v;
          
          return v.Substring(0, 1).ToLower() + (v.Length>1?(atl?v.Substring(1).ToLower():v.Substring(1)):"");
        }
        
        /// <summary>
        /// 将Oracle表名或字段名转换成JAVA表名或字段名
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public static string ConvertOTJ(string v)
        {
            string result = "";
            string[] temp = v.Split('_');
            
            foreach(string o in temp)
            {
                result += InitialToOpper(o,true);
            }
            
            return result;
        }
	}
}
