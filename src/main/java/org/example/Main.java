import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static String url = "https://vulcanvalues.com/grow-a-garden/stock";

    public static void StockFetch() {
        JSONObject data = new JSONObject();
        JSONArray EggArray = new JSONArray();
        JSONArray SeedArray = new JSONArray();
        JSONArray GearArray = new JSONArray();

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".space-y-2");

            for (var current : elements) {
                String stock = current.select("li > span").text();


                String[] seedList = {"Carrot", "Strawberry", "Blueberry", "Orange Tulip", "Tomato", "Bamboo", "Corn", "Watermelon", "Apple", "Coconut", "Daffodil", "Cactus", "Pepper", "Dragon Fruit"};
                String[] gearList = {"Watering Can", "Trowel", "Recall Wrench",  "Basic Sprinkler", "Advanced Sprinkler", "Godly Sprinkler", "Lightning Rod", "Master Sprinkler", "Favorite Tool"};
                String[] eggList = {"Common Egg", "Uncommon Egg", "Rare Egg", "Legendary Egg", "Bug Egg"};

                for (String LoopJson : seedList) {
                    if (stock.contains(LoopJson)) {
                        SeedArray.put(LoopJson);
                    }
                }
                for (String LoopJson : gearList) {
                    if (stock.contains(LoopJson)) {
                        GearArray.put(LoopJson);
                    }
                }
                String[] parts = stock.split("(?<=Egg)\\s+");

                for (String part : parts) {
                    for (String LoopJson : eggList) {
                        if (part.contains(LoopJson)) {
                            EggArray.put(LoopJson);
                        }
                    }
                }
                System.out.println(stock);
            }

            data.put("Seed", SeedArray);
            data.put("Gear", GearArray);
            data.put("Egg", EggArray);

            System.out.println(data.toString(2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StockFetch();
    }
}
