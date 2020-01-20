package com.demo.yxhmvp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

public class StoryListBean extends BaseModel {

    /**
     * booklist : [{"BookId":280,"BookType":0,"BookName":"《故事会文摘版》2018年10月","BookAbout":"《故事会文摘版》2018年10月","BookImg":"http://storyimg.storychina.cn//uploads/20181015/201810150158144881318.jpg","BookReader":31,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-10-15","BookTerm":"2018年10月","userFav":0,"userUp":0,"userBuy":0},{"BookId":279,"BookType":0,"BookName":"2018年10月下半月","BookAbout":"开卷故事\t\t2\r\n笑话16则\t李承龙等\t4\r\n网文热读\r\n温情的目光\t李忠元\t8\r\n传闻轶事\r\n神奇的\t魏\u2003炜\t10\r\n压轴菜\t刘江波\t30\r\n诙段子\t\t15\r\n新传说\r\n代养\t徐树建\t17\r\n两瓶好酒\t刘春玲\t21\r\n车祸之后\t黎木兮\t24\r\n要命的门\t傻  雀\t27\r\n抢手的生意\t王君超\t37\r\n音箱\t孙灿灿\t41\r\n一念之差\t叶林生\t44\r\n民间故事金库\r\n徐茂公当家\t李  亮\t34\r\n3分","BookImg":"http://storyimg.storychina.cn//uploads/20181015/201810150142353518558.jpg","BookReader":46,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-10-15","BookTerm":"2018年10月下","userFav":0,"userUp":0,"userBuy":0},{"BookId":278,"BookType":0,"BookName":"2018年10月上半月","BookAbout":"开卷故事\t2\r\n笑话15则\t牧  之 等   4\r\n新传说\r\n外卖风波\t玉  琨   8\r\n非常篮球赛\u2026\t侯晓琪  12\r\n惊魂一夜\t黄  平  21\r\n鱼跑哪去了\t翟怀舒  25\r\n借粮\t徐嘉青  28\r\n\u201c杠王\u201d抬棺\t刘建平  31\r\n央企故事 \r\n神手高凤林\t常  轩 吴  思  17  \r\n传闻轶事\r\n代酒\t王春迪  35\r\n一箭待发\t俏俏红  86\r\n外国文学故事鉴赏\r\n月夜的桌布\t 3","BookImg":"http://storyimg.storychina.cn//uploads/20180929/201809290346445761345.jpg","BookReader":35,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-09-29","BookTerm":"2018年10月上","userFav":0,"userUp":0,"userBuy":0},{"BookId":277,"BookType":0,"BookName":"《故事会文摘版》2018年9月","BookAbout":"《故事会文摘版》2018年9月","BookImg":"http://storyimg.storychina.cn//uploads/20180914/201809140156404351419.jpg","BookReader":19,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-09-14","BookTerm":"2018年9月","userFav":0,"userUp":0,"userBuy":0}]
     * errcode : 0
     * errmsg : 执行成功
     * countNum : 102
     * maxPage : 26
     */

    private int errcode;
    private String errmsg;
    private int countNum;
    private int maxPage;
    private List<BooklistBean> booklist;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<BooklistBean> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<BooklistBean> booklist) {
        this.booklist = booklist;
    }

    public static class BooklistBean {
        /**
         * BookId : 280
         * BookType : 0
         * BookName : 《故事会文摘版》2018年10月
         * BookAbout : 《故事会文摘版》2018年10月
         * BookImg : http://storyimg.storychina.cn//uploads/20181015/201810150158144881318.jpg
         * BookReader : 31
         * BookScore : 0
         * BookWriter : null
         * BookSizeWords : 0
         * BookMoneyAll : 0
         * BookMoneyMin : 0
         * BookOver : 0
         * BookDateUp : 2018-10-15
         * BookTerm : 2018年10月
         * userFav : 0
         * userUp : 0
         * userBuy : 0
         */

        private int BookId;
        private int BookType;
        private String BookName;
        private String BookAbout;
        private String BookImg;
        private int BookReader;
        private int BookScore;
        private Object BookWriter;
        private int BookSizeWords;
        private int BookMoneyAll;
        private int BookMoneyMin;
        private int BookOver;
        private String BookDateUp;
        private String BookTerm;
        private int userFav;
        private int userUp;
        private int userBuy;

        public int getBookId() {
            return BookId;
        }

        public void setBookId(int BookId) {
            this.BookId = BookId;
        }

        public int getBookType() {
            return BookType;
        }

        public void setBookType(int BookType) {
            this.BookType = BookType;
        }

        public String getBookName() {
            return BookName;
        }

        public void setBookName(String BookName) {
            this.BookName = BookName;
        }

        public String getBookAbout() {
            return BookAbout;
        }

        public void setBookAbout(String BookAbout) {
            this.BookAbout = BookAbout;
        }

        public String getBookImg() {
            return BookImg;
        }

        public void setBookImg(String BookImg) {
            this.BookImg = BookImg;
        }

        public int getBookReader() {
            return BookReader;
        }

        public void setBookReader(int BookReader) {
            this.BookReader = BookReader;
        }

        public int getBookScore() {
            return BookScore;
        }

        public void setBookScore(int BookScore) {
            this.BookScore = BookScore;
        }

        public Object getBookWriter() {
            return BookWriter;
        }

        public void setBookWriter(Object BookWriter) {
            this.BookWriter = BookWriter;
        }

        public int getBookSizeWords() {
            return BookSizeWords;
        }

        public void setBookSizeWords(int BookSizeWords) {
            this.BookSizeWords = BookSizeWords;
        }

        public int getBookMoneyAll() {
            return BookMoneyAll;
        }

        public void setBookMoneyAll(int BookMoneyAll) {
            this.BookMoneyAll = BookMoneyAll;
        }

        public int getBookMoneyMin() {
            return BookMoneyMin;
        }

        public void setBookMoneyMin(int BookMoneyMin) {
            this.BookMoneyMin = BookMoneyMin;
        }

        public int getBookOver() {
            return BookOver;
        }

        public void setBookOver(int BookOver) {
            this.BookOver = BookOver;
        }

        public String getBookDateUp() {
            return BookDateUp;
        }

        public void setBookDateUp(String BookDateUp) {
            this.BookDateUp = BookDateUp;
        }

        public String getBookTerm() {
            return BookTerm;
        }

        public void setBookTerm(String BookTerm) {
            this.BookTerm = BookTerm;
        }

        public int getUserFav() {
            return userFav;
        }

        public void setUserFav(int userFav) {
            this.userFav = userFav;
        }

        public int getUserUp() {
            return userUp;
        }

        public void setUserUp(int userUp) {
            this.userUp = userUp;
        }

        public int getUserBuy() {
            return userBuy;
        }

        public void setUserBuy(int userBuy) {
            this.userBuy = userBuy;
        }
    }
}
