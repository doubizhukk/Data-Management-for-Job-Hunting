package job.task;

public class MathSalary {
    public static Integer[] getSalary(String salaryStr) {
        // min and max salary
        Integer[] salary = new Integer[2];

        //get the unit for date
        String date = salaryStr.substring(salaryStr.length() - 1, salaryStr.length());
        // if it is daily, multiply by 240
        if (!"月".equals(date) && !"年".equals(date)) {
            salaryStr = salaryStr.substring(0, salaryStr.length() - 2);
            salary[0] = salary[1] = str2Num(salaryStr, 240);
            return salary;
        }

        String unit = salaryStr.substring(salaryStr.length() - 3, salaryStr.length() - 2);
        String[] salarys = salaryStr.substring(0, salaryStr.length() - 3).split("-");

        salary[0] = mathSalary(date, unit, salarys[0]);
        salary[1] = mathSalary(date, unit, salarys[1]);

        return salary;

    }
    private static Integer mathSalary(String date, String unit, String salaryStr) {
        Integer salary = 0;


        if ("万".equals(unit)) {

            salary = str2Num(salaryStr, 1000);
        } else {

            salary = str2Num(salaryStr, 100);
        }


        if ("月".equals(date)) {

            salary = str2Num(salary.toString(), 12);
        }

        return salary;
    }
    private static int str2Num(String salaryStr, int num) {
        try {

            Number result = Float.parseFloat(salaryStr) * num;
            return result.intValue();
        } catch (Exception e) {
        }
        return 0;
    }
}
