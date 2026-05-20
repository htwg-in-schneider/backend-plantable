package com.plantable.backend.config;

import com.plantable.backend.model.CareLevel;
import com.plantable.backend.model.LightRequirement;
import com.plantable.backend.model.Plant;
import com.plantable.backend.repository.PlantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlantRepository plantRepository;

    public DataLoader(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public void run(String... args) {
        if (plantRepository.count() > 0) {
            return;
        }

        List<Plant> plants = List.of(
            new Plant(
                "Monstera deliciosa", "Fensterblatt", "monstera-deliciosa",
                "Ein lebendiges Kunstwerk – mit ihren großen, gefensterten Blättern bringt sie tropisches Flair in jeden Raum.",
                "https://images.unsplash.com/photo-1614594975525-e45190c55d0b?w=800",
                CareLevel.EASY, LightRequirement.BRIGHT,
                false, true, "Mittelamerika"
            ),
            new Plant(
                "Calathea orbifolia", "Korbmarante", "calathea-orbifolia",
                "Mit ihren großen, runden Blättern und silbrigen Streifen ist sie eine echte Schönheit für schattigere Plätze.",
                "https://images.unsplash.com/photo-1632324343636-9c70c97c9d3a?w=800",
                CareLevel.MEDIUM, LightRequirement.MEDIUM,
                true, false, "Bolivien"
            ),
            new Plant(
                "Sansevieria laurentii", "Bogenhanf", "sansevieria-laurentii",
                "Robust und pflegeleicht, perfekt für Anfänger. Filtert die Luft besonders effektiv.",
                "https://images.unsplash.com/photo-1599598425947-5fdb1aafa0a4?w=800",
                CareLevel.EASY, LightRequirement.LOW,
                false, true, "Westafrika"
            ),
            new Plant(
                "Ficus lyrata", "Geigenblattfeige", "ficus-lyrata",
                "Imposant und elegant – die Geigenblattfeige ist ein Statement-Piece für jeden hellen Raum.",
                "https://images.unsplash.com/photo-1597055181449-b3973ad9c8e2?w=800",
                CareLevel.HARD, LightRequirement.BRIGHT,
                false, true, "Westafrika"
            ),
            new Plant(
                "Senecio rowleyanus", "Erbsenpflanze", "senecio-rowleyanus",
                "Hängende Sukkulente mit perlenartigen Blättern – ein verspielter Hingucker für Regale und Ampeln.",
                "https://images.unsplash.com/photo-1632321305349-9b916ed4a548?w=800",
                CareLevel.MEDIUM, LightRequirement.DIRECT,
                false, false, "Südwestafrika"
            ),
            new Plant(
                "Scindapsus pictus", "Silberblatt-Efeutute", "scindapsus-pictus",
                "Rankende Schönheit mit silbrig gemusterten Blättern – wächst in fast jeder Lichtsituation.",
                "https://images.unsplash.com/photo-1604762524889-3e2fcc145683?w=800",
                CareLevel.EASY, LightRequirement.MEDIUM,
                true, true, "Südostasien"
            ),
            new Plant(
                "Pilea peperomioides", "Ufopflanze", "pilea-peperomioides",
                "Mit ihren runden, münzenförmigen Blättern ein echter Trendliebling. Sehr einfach zu vermehren.",
                "https://images.unsplash.com/photo-1622383563227-04401ab4e5ea?w=800",
                CareLevel.EASY, LightRequirement.BRIGHT,
                true, false, "China"
            ),
            new Plant(
                "Strelitzia nicolai", "Weiße Paradiesvogelblume", "strelitzia-nicolai",
                "Bringt mit ihren bananenartigen Blättern tropisches Flair ins Wohnzimmer und kann beeindruckende Größen erreichen.",
                "https://images.unsplash.com/photo-1597055181300-e3633a917207?w=800",
                CareLevel.MEDIUM, LightRequirement.BRIGHT,
                false, false, "Südafrika"
            ),
            new Plant(
                "Alocasia polly", "Pfeilblatt", "alocasia-polly",
                "Dramatische pfeilförmige Blätter mit silbrigen Adern – ein Hingucker, der etwas Aufmerksamkeit braucht.",
                "https://images.unsplash.com/photo-1632321305349-9b916ed4a548?w=800",
                CareLevel.HARD, LightRequirement.BRIGHT,
                false, false, "Südostasien"
            ),
            new Plant(
                "Epipremnum aureum", "Goldene Efeutute", "epipremnum-aureum",
                "Die wohl pflegeleichteste Rankpflanze – wächst auch bei wenig Licht und vergibt Pflegefehler.",
                "https://images.unsplash.com/photo-1600411833196-7c1f6b1a8b90?w=800",
                CareLevel.EASY, LightRequirement.LOW,
                false, true, "Französisch-Polynesien"
            ),
            new Plant(
                "Zamioculcas zamiifolia", "Glücksfeder", "zamioculcas-zamiifolia",
                "Nahezu unzerstörbar – verzeiht Vergessen beim Gießen und gedeiht auch in dunklen Ecken.",
                "https://images.unsplash.com/photo-1632207691143-643e2a9a9361?w=800",
                CareLevel.EASY, LightRequirement.LOW,
                false, true, "Ostafrika"
            ),
            new Plant(
                "Spathiphyllum wallisii", "Einblatt", "spathiphyllum-wallisii",
                "Elegante weiße Blütenscheiden auf glänzend grünem Laub – signalisiert klar, wann sie Wasser braucht.",
                "https://images.unsplash.com/photo-1616500592049-30fcf2a39024?w=800",
                CareLevel.EASY, LightRequirement.MEDIUM,
                false, true, "Mittel- und Südamerika"
            ),
            new Plant(
                "Chlorophytum comosum", "Grünlilie", "chlorophytum-comosum",
                "Klassiker der Zimmerpflanzen mit hängenden Kindeln – ideal für Anfänger und absolut haustierfreundlich.",
                "https://images.unsplash.com/photo-1572688484438-313a6e50c333?w=800",
                CareLevel.EASY, LightRequirement.BRIGHT,
                true, true, "Südafrika"
            ),
            new Plant(
                "Aloe vera", "Echte Aloe", "aloe-vera",
                "Sukkulente Heilpflanze mit fleischigen Blättern – braucht viel Sonne und wenig Wasser.",
                "https://images.unsplash.com/photo-1509423350716-97f9360b4e09?w=800",
                CareLevel.EASY, LightRequirement.DIRECT,
                false, false, "Arabische Halbinsel"
            ),
            new Plant(
                "Ficus elastica", "Gummibaum", "ficus-elastica",
                "Robuster Klassiker mit ledrigen, glänzenden Blättern – wächst über Jahre zu einem stattlichen Baum heran.",
                "https://images.unsplash.com/photo-1604762525159-12b35d51a4a4?w=800",
                CareLevel.EASY, LightRequirement.BRIGHT,
                false, true, "Südostasien"
            ),
            new Plant(
                "Philodendron scandens", "Herzblatt-Philodendron", "philodendron-scandens",
                "Schnellwachsende Kletterpflanze mit herzförmigen Blättern – flexibel als Hängepflanze oder am Moosstab.",
                "https://images.unsplash.com/photo-1632320695395-f5fadddc7c47?w=800",
                CareLevel.EASY, LightRequirement.MEDIUM,
                false, true, "Mittelamerika"
            ),
            new Plant(
                "Maranta leuconeura", "Pfeilwurz", "maranta-leuconeura",
                "Bekannt als Gebetspflanze – ihre Blätter falten sich abends zusammen wie zum Gebet.",
                "https://images.unsplash.com/photo-1606057275105-a1ba0f6e8e3a?w=800",
                CareLevel.MEDIUM, LightRequirement.MEDIUM,
                true, false, "Brasilien"
            ),
            new Plant(
                "Crassula ovata", "Geldbaum", "crassula-ovata",
                "Sukkulenter Baum mit dicken, glänzenden Blättern – soll laut Feng Shui Glück und Wohlstand bringen.",
                "https://images.unsplash.com/photo-1459411552884-841db9b3cc2a?w=800",
                CareLevel.EASY, LightRequirement.DIRECT,
                false, false, "Südafrika"
            ),
            new Plant(
                "Nephrolepis exaltata", "Schwertfarn", "nephrolepis-exaltata",
                "Üppiger Farn mit feinen Wedeln – liebt hohe Luftfeuchtigkeit, daher perfekt fürs Badezimmer.",
                "https://images.unsplash.com/photo-1599598425947-5fdb1aafa0a4?w=800",
                CareLevel.MEDIUM, LightRequirement.MEDIUM,
                true, true, "Tropen weltweit"
            ),
            new Plant(
                "Hoya carnosa", "Wachsblume", "hoya-carnosa",
                "Rankende Pflanze mit duftenden, sternförmigen Blüten und dicken, fast künstlich wirkenden Blättern.",
                "https://images.unsplash.com/photo-1612181673440-9ff7e87daf6c?w=800",
                CareLevel.EASY, LightRequirement.BRIGHT,
                true, false, "Australien & Ostasien"
            )
        );

        plantRepository.saveAll(plants);
        System.out.println(plants.size() + " Pflanzen geladen");
    }
}