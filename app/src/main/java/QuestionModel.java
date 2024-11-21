public class QuestionModel {
    public class Questionmodel{
        private String question;
        private String OptionA;
        private String OptionB;
        private String OptionC;
        private String OptionD;
        private int correctAns;
        public Questionmodel(String question, String optionA,String optionB, String optionC, String optionD, int correctAns){
            this.question = question;
            this.OptionA = optionA;
            this.OptionB = optionC;
            this.OptionC = optionC;
            this.OptionD = optionD;
            this.correctAns = correctAns;
        }

        public String OptionA() {
            return OptionA;
        }

        public void setOptionA(String optionA) {
            OptionA = optionA;
        }

        public String OptionB() {
            return OptionB;
        }

        public void setOptionB(String optionB) {
            OptionB = optionB;
        }

        public String OptionC() {
            return OptionC;
        }

        public void setOptionC(String optionC) {
            OptionC = optionC;
        }

        public String OptionD() {
            return OptionD;
        }

        public void setOptionD(String optionD) {
            OptionD = optionD;
        }

        public String question() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int correctAns() {
            return correctAns;
        }

        public void setCorrectAns(int correctAns) {
            this.correctAns = correctAns;
        }
    }
}
