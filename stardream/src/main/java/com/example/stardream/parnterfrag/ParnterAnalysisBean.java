package com.example.stardream.parnterfrag;

public class ParnterAnalysisBean {

    /**
     * reason : success
     * result : {"men":"白羊","women":"金牛","zhishu":"70","bizhong":"54:46","xiangyue":"80","tcdj":"60","jieguo":"小吵小闹的一对 ","lianai":"白羊座性急，金牛座慢半拍，这两个星座在一起就像龟兔赛跑，牛儿永远跟在羊儿身后。你们在一起更多的互补作用，金牛座总是无怨无悔地为性急的白羊座收拾善后，默默地付出。有时你们也会像一对童心未泯的孩子，童心很重，在一定程度，牛儿还蛮依赖羊儿。","zhuyi":"白羊座和金牛座在一起，其实也是一对孩子气蛮重的组合，他们都有着童心未泯的个性。牛儿虽然很能容忍、不妒忌，但占有欲强，羊儿个性豪迈，喜欢交际，牛儿若爱上羊儿，可以在一定程度上给予对方更大的自由和空间。同时牛儿也不必时时为羊儿善后，不妨放开心胸促使不要学习平稳冷静，带着羊儿向前，在生活上学习取长补短。"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * men : 白羊
         * women : 金牛
         * zhishu : 70
         * bizhong : 54:46
         * xiangyue : 80
         * tcdj : 60
         * jieguo : 小吵小闹的一对
         * lianai : 白羊座性急，金牛座慢半拍，这两个星座在一起就像龟兔赛跑，牛儿永远跟在羊儿身后。你们在一起更多的互补作用，金牛座总是无怨无悔地为性急的白羊座收拾善后，默默地付出。有时你们也会像一对童心未泯的孩子，童心很重，在一定程度，牛儿还蛮依赖羊儿。
         * zhuyi : 白羊座和金牛座在一起，其实也是一对孩子气蛮重的组合，他们都有着童心未泯的个性。牛儿虽然很能容忍、不妒忌，但占有欲强，羊儿个性豪迈，喜欢交际，牛儿若爱上羊儿，可以在一定程度上给予对方更大的自由和空间。同时牛儿也不必时时为羊儿善后，不妨放开心胸促使不要学习平稳冷静，带着羊儿向前，在生活上学习取长补短。
         */

        private String men;
        private String women;
        private String zhishu;
        private String bizhong;
        private String xiangyue;
        private String tcdj;
        private String jieguo;
        private String lianai;
        private String zhuyi;

        public String getMen() {
            return men;
        }

        public void setMen(String men) {
            this.men = men;
        }

        public String getWomen() {
            return women;
        }

        public void setWomen(String women) {
            this.women = women;
        }

        public String getZhishu() {
            return zhishu;
        }

        public void setZhishu(String zhishu) {
            this.zhishu = zhishu;
        }

        public String getBizhong() {
            return bizhong;
        }

        public void setBizhong(String bizhong) {
            this.bizhong = bizhong;
        }

        public String getXiangyue() {
            return xiangyue;
        }

        public void setXiangyue(String xiangyue) {
            this.xiangyue = xiangyue;
        }

        public String getTcdj() {
            return tcdj;
        }

        public void setTcdj(String tcdj) {
            this.tcdj = tcdj;
        }

        public String getJieguo() {
            return jieguo;
        }

        public void setJieguo(String jieguo) {
            this.jieguo = jieguo;
        }

        public String getLianai() {
            return lianai;
        }

        public void setLianai(String lianai) {
            this.lianai = lianai;
        }

        public String getZhuyi() {
            return zhuyi;
        }

        public void setZhuyi(String zhuyi) {
            this.zhuyi = zhuyi;
        }
    }
}
