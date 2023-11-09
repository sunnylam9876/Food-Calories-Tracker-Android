package com.example.nutritiontracking.Utility;

import com.example.nutritiontracking.db.entity.FoodLog;

import java.util.Calendar;
import java.util.List;

public class Utility {

    static final String[] TIPS = {
                    "Traffic light labelling on the front of packs provides an at-a-glance guide to just how healthy (or not) a food is – but don’t forget to check the nutrition panel on the back too, which will help you compare brands, as well as the ingredients list to check what a food really contains.",
                    "Acidic foods such as lemon will lower the glycaemic index (GI) of pasta and rice, which will slow the conversion of starch to sugar.",
                    "The more you damage garlic (and onion) cell walls, the more anticlotting sulphur phytochemicals – which help reduce the risk of heart disease and stroke – you release, which is why crushing is better than cutting. Resting crushed garlic for 15 minutes before use will boost levels even further.",
                    "Hide sweets and biscuits away (or better still don’t buy them). Studies show that if you keep the biscuit tin or 'naughty' treats where you can see them, you’re more likely to succumb.",
                    "Healthy eating is much easier if you plan your days. Set aside time at the weekend to write a healthy recipe menu plan for the next seven days. This will help you become more organised, and the investment in managing your meals will pay dividends when your energy levels and good intentions may be flagging later in the week.",
                    "Variety may be the spice of life but it’s also the key to a healthy balanced diet. No single food or food group can provide all the nutrients your body needs to stay healthy. Eating a wide range of foods is the best way to make sure your diet contains all the nutrients it needs.",
                    "Cooking from scratch puts you in control. It means you can improve family favourites by reducing salt, choosing healthy fats and adding ingredients such as beans and wholegrains to boost the fibre.",
                    "A good source of fibre and magnesium.",
                    "A good source of omega-3 oils.",
                    "Helps lower blood sugar and cholesterol levels.",
                    "A great source of zinc, important for a healthy immune system.",
                    "In fact, add any fruit – fresh, dried, canned or frozen.",
                    "Save it for teatime – the tannin in tea reduces the absorption of iron from food, so try not to drink it with meals.",
                    "Canned beans and pulses such as lentils and chickpeas are high in fibre, and just three heaped tablespoons count as one of your 5-a-day (although no matter how much you eat, they will only count as one portion per day). Try adding them to soups, casseroles or salads.",
                    "Pasta cooked al dente has a lower GI, which means it is digested more slowly, helping you to feel full for longer while keeping blood sugar levels stable",
                    "Frozen peas are a great standby and, like other frozen vegetables, are a great source of vitamin C. In fact, quickly freezing freshly harvested produce can lock in the vitamins, so frozen fruit and veg often contain higher levels of vitamin C than fresh.",
                    "Not drinking enough water is one of the most common causes of headaches, poor concentration and tiredness. Long-term dehydration can lead to constipation and urinary tract infections. Download an app such as Waterlogged to remind you to drink regularly. ",
                    "Instead of butter, spread bread with mayo, which contains unsaturated fat and is easier to spread, and choose wholemeal bread too. Then add lots of vegetables to the filling.",
                    "Eggs have a lot to offer – they’re one of the few dietary sources of vitamin D as well as providing good amounts of vitamins A, E, B2 and B12. They are also rich in lecithin and choline, which play an important role in brain function and memory. And don’t just keep them for breakfast – they make a cracking lunch or dinner!",
                    "If you haven’t tried internet food shopping, why not give it a go? It’s quick and easy and you’re less likely to be tempted to buy unhealthy treats and snacks.",
                    "Oats are rich in beta glucan, a type of soluble fibre that helps the body get rid of unhealthy cholesterol. Studies show that eating just two tablespoons of oats as part of a low-fat diet can help to reduce cholesterol levels. Choose jumbo oats or stone-ground oats rather than the fine-ground types, because they will be absorbed more slowly.",
                    "Reduce the calorie and fat content of mayo by mixing it 50:50 with plain yogurt. Pep up the flavour by adding chopped fresh herbs, garlic or a dash of mustard.",
                    "Don’t rely on a strong black coffee to give you a kick-start in the morning. Take the time to eat a proper breakfast and you’ll reap the rewards throughout the rest of the day. Studies show that people who eat breakfast are less likely to be overweight and are also less likely to suffer from colds and flu. ",
                    "Allow yourself a treat now and again. If you include the foods you like to eat in your diet – but ‘naughty’ ones in moderation rather than as a daily treat – you’re much more likely to stick with your healthy resolutions in the long term.",
                    "Research from Penn State University found that people who ate an apple 15 minutes before lunch consumed 190 fewer calories than those who snacked on something else or ate lunch on an empty stomach.",
                    "Cooking carrots helps to break down their tough cell walls, releasing the carotene contained within the cells. The body can absorb around 50 per cent more carotene from cooked carrots than raw – and adding a little butter or olive oil helps to boost the absorption even more. ",
                    "Nuts and seeds are a good source of vitamin E and heart-friendly unsaturated fats. People who eat nuts regularly are less likely to suffer from heart disease. All nuts are healthy but different varieties contain different nutrients, so ring the changes.",
                    "Choose extra-mature rather than mild cheese when you’re cooking. You won’t need to use as much to get the same level of flavour and so will reduce the calorie, fat and salt content of your dish, without losing out on taste.",
                    "Great for making homemade soup, a healthy lunch that can count as two or even three of your 5-a-day.",
                    "Although most of us are well aware of the recommendation to eat at least five portions of fruit and veg a day, only one in three of us manages to reach the target. Starting the day with a freshly made juice is an easy way to get your first of the day.",
                    "At the end of a busy day, cooking a meal is probably the last thing you want to do – which is why these gadgets can be such a good friend. What’s better than arriving home to a delicious stew or casserole? Slow cookers aren’t expensive and are perfect for cooking cheaper cuts of meat. You can even make porridge, cakes and jam in them. ",
    };

    public static String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    public static String getMonthFormat(int month) {
        switch (month) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "Jan";
    }

    public static String getTodayDate() {
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month,year);
    }

    public static int getToday_Day() {
        Calendar cal= Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static FoodLog NutritionCalculation(List<FoodLog> foodLogList) {
        double calories = 0, total_fat = 0, s_fat = 0, t_fat = 0, cholesterol = 0, sodium = 0;
        double total_carbohydrates = 0, dietary_fiber = 0, sugars = 0, protein = 0, vitamin_a = 0;
        double vitamin_c = 0, vitamin_d = 0, calcium = 0, iron = 0, potassium = 0;

        for (int i = 0; i < foodLogList.size(); i++) {
            calories += foodLogList.get(i).getCalories();
            total_fat += foodLogList.get(i).getTotal_fat();
            s_fat += foodLogList.get(i).getS_fat();
            t_fat += foodLogList.get(i).getT_fat();
            cholesterol += foodLogList.get(i).getCholesterol();
            sodium += foodLogList.get(i).getSodium();
            total_carbohydrates += foodLogList.get(i).getTotal_carbohydrates();
            dietary_fiber += foodLogList.get(i).getDietary_fiber();
            sugars += foodLogList.get(i).getSugars();
            protein += foodLogList.get(i).getProtein();
            vitamin_a += foodLogList.get(i).getVitamin_a();
            vitamin_c += foodLogList.get(i).getVitamin_c();
            vitamin_d += foodLogList.get(i).getVitamin_d();
            calcium += foodLogList.get(i).getCalcium();
            iron += foodLogList.get(i).getIron();
            potassium += foodLogList.get(i).getPotassium();
        }
        FoodLog foodLog = new FoodLog(0,"no", "no", "no", "no",
                            "no", "no", 0,0, calories, total_fat,
                                        s_fat, t_fat, cholesterol, sodium, total_carbohydrates, dietary_fiber,
                                        sugars, protein, vitamin_a, vitamin_c, vitamin_d, calcium, iron, potassium);


        return foodLog;
    }

    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static double calculate_BMI(double weight, double height) {
        double bmi = weight / ((height/100) * (height/100));
        return round(bmi,1);
    }

    public static String getTips(int day) {


        int todayIndex = day - 1;

        if (todayIndex >= 0 && todayIndex < TIPS.length) {
            return TIPS[todayIndex];
        } else {
            return "No tip for today";
        }
    }
}
