package com.plantable.backend.config;

import com.plantable.backend.model.CareLevel;
import com.plantable.backend.model.LightRequirement;
import com.plantable.backend.model.Plant;
import com.plantable.backend.model.Tag;
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

        Plant monstera = new Plant(
            "Monstera deliciosa", "Fensterblatt", "monstera-deliciosa",
            "Ein lebendiges Kunstwerk – mit ihren großen, gefensterten Blättern bringt sie tropisches Flair in jeden Raum.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/monstera_deliciosa.png",
            CareLevel.EASY, LightRequirement.BRIGHT, false, true, "Mittelamerika");
        addTags(monstera, "Anfänger", "Luftreinigend", "Tropisch", "Zimmerpflanze");

        Plant calathea = new Plant(
            "Calathea orbifolia", "Korbmarante", "calathea-orbifolia",
            "Mit ihren großen, runden Blättern und silbrigen Streifen ist sie eine echte Schönheit für schattigere Plätze.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/calathea_orbifolia.png",
            CareLevel.MEDIUM, LightRequirement.MEDIUM, true, false, "Bolivien");
        addTags(calathea, "Haustierfreundlich", "Schattenpflanze", "Tropisch");

        Plant sansevieria = new Plant(
            "Sansevieria laurentii", "Bogenhanf", "sansevieria-laurentii",
            "Robust und pflegeleicht, perfekt für Anfänger. Filtert die Luft besonders effektiv.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/sansevieria_laurentii.png",
            CareLevel.EASY, LightRequirement.LOW, false, true, "Westafrika");
        addTags(sansevieria, "Anfänger", "Luftreinigend", "Schattenpflanze");

        Plant ficusLyrata = new Plant(
            "Ficus lyrata", "Geigenblattfeige", "ficus-lyrata",
            "Imposant und elegant – die Geigenblattfeige ist ein Statement-Piece für jeden hellen Raum.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/ficus_lyrata.png",
            CareLevel.HARD, LightRequirement.BRIGHT, false, true, "Westafrika");
        addTags(ficusLyrata, "Experte", "Luftreinigend", "Zimmerpflanze");

        Plant senecio = new Plant(
            "Senecio rowleyanus", "Erbsenpflanze", "senecio-rowleyanus",
            "Hängende Sukkulente mit perlenartigen Blättern – ein verspielter Hingucker für Regale und Ampeln.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/senecio_rowleyanus.png",
            CareLevel.MEDIUM, LightRequirement.DIRECT, false, false, "Südwestafrika");
        addTags(senecio, "Sukkulente", "Hängepflanze", "Sonnenliebend");

        Plant scindapsus = new Plant(
            "Scindapsus pictus", "Silberblatt-Efeutute", "scindapsus-pictus",
            "Rankende Schönheit mit silbrig gemusterten Blättern – wächst in fast jeder Lichtsituation.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/scindapsus_pictus.png",
            CareLevel.EASY, LightRequirement.MEDIUM, true, true, "Südostasien");
        addTags(scindapsus, "Anfänger", "Rankpflanze", "Haustierfreundlich", "Luftreinigend");

        Plant pilea = new Plant(
            "Pilea peperomioides", "Ufopflanze", "pilea-peperomioides",
            "Mit ihren runden, münzenförmigen Blättern ein echter Trendliebling. Sehr einfach zu vermehren.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/pilea_peperomioides.png",
            CareLevel.EASY, LightRequirement.BRIGHT, true, false, "China");
        addTags(pilea, "Anfänger", "Haustierfreundlich", "Zimmerpflanze");

        Plant strelitzia = new Plant(
            "Strelitzia nicolai", "Weiße Paradiesvogelblume", "strelitzia-nicolai",
            "Bringt mit ihren bananenartigen Blättern tropisches Flair ins Wohnzimmer und kann beeindruckende Größen erreichen.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/strelitzia_nicolai.png",
            CareLevel.MEDIUM, LightRequirement.BRIGHT, false, false, "Südafrika");
        addTags(strelitzia, "Tropisch", "Sonnenliebend", "Zimmerpflanze");

        Plant alocasia = new Plant(
            "Alocasia polly", "Pfeilblatt", "alocasia-polly",
            "Dramatische pfeilförmige Blätter mit silbrigen Adern – ein Hingucker, der etwas Aufmerksamkeit braucht.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/alocasia_polly.png",
            CareLevel.HARD, LightRequirement.BRIGHT, false, false, "Südostasien");
        addTags(alocasia, "Experte", "Tropisch");

        Plant epipremnum = new Plant(
            "Epipremnum aureum", "Goldene Efeutute", "epipremnum-aureum",
            "Die wohl pflegeleichteste Rankpflanze – wächst auch bei wenig Licht und vergibt Pflegefehler.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/epipremnum_aureum.png",
            CareLevel.EASY, LightRequirement.LOW, false, true, "Französisch-Polynesien");
        addTags(epipremnum, "Anfänger", "Rankpflanze", "Luftreinigend", "Schattenpflanze");

        Plant zamioculcas = new Plant(
            "Zamioculcas zamiifolia", "Glücksfeder", "zamioculcas-zamiifolia",
            "Nahezu unzerstörbar – verzeiht Vergessen beim Gießen und gedeiht auch in dunklen Ecken.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/zamioculcas_zamiifolia.png",
            CareLevel.EASY, LightRequirement.LOW, false, true, "Ostafrika");
        addTags(zamioculcas, "Anfänger", "Luftreinigend", "Schattenpflanze");

        Plant spathiphyllum = new Plant(
            "Spathiphyllum wallisii", "Einblatt", "spathiphyllum-wallisii",
            "Elegante weiße Blütenscheiden auf glänzend grünem Laub – signalisiert klar, wann sie Wasser braucht.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/spathiphyllum_wallisii.png",
            CareLevel.EASY, LightRequirement.MEDIUM, false, true, "Mittel- und Südamerika");
        addTags(spathiphyllum, "Anfänger", "Luftreinigend", "Blühend");

        Plant chlorophytum = new Plant(
            "Chlorophytum comosum", "Grünlilie", "chlorophytum-comosum",
            "Klassiker der Zimmerpflanzen mit hängenden Kindeln – ideal für Anfänger und absolut haustierfreundlich.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/chlorophytum_comosum.png",
            CareLevel.EASY, LightRequirement.BRIGHT, true, true, "Südafrika");
        addTags(chlorophytum, "Anfänger", "Haustierfreundlich", "Luftreinigend", "Hängepflanze");

        Plant aloe = new Plant(
            "Aloe vera", "Echte Aloe", "aloe-vera",
            "Sukkulente Heilpflanze mit fleischigen Blättern – braucht viel Sonne und wenig Wasser.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/aloe_vera.png",
            CareLevel.EASY, LightRequirement.DIRECT, false, false, "Arabische Halbinsel");
        addTags(aloe, "Anfänger", "Sukkulente", "Sonnenliebend", "Heilpflanze");

        Plant ficusElastica = new Plant(
            "Ficus elastica", "Gummibaum", "ficus-elastica",
            "Robuster Klassiker mit ledrigen, glänzenden Blättern – wächst über Jahre zu einem stattlichen Baum heran.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/ficus_elastica.png",
            CareLevel.EASY, LightRequirement.BRIGHT, false, true, "Südostasien");
        addTags(ficusElastica, "Anfänger", "Luftreinigend", "Zimmerpflanze");

        Plant philodendron = new Plant(
            "Philodendron scandens", "Herzblatt-Philodendron", "philodendron-scandens",
            "Schnellwachsende Kletterpflanze mit herzförmigen Blättern – flexibel als Hängepflanze oder am Moosstab.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/philodendron_scandens.png",
            CareLevel.EASY, LightRequirement.MEDIUM, false, true, "Mittelamerika");
        addTags(philodendron, "Anfänger", "Rankpflanze", "Luftreinigend", "Tropisch");

        Plant maranta = new Plant(
            "Maranta leuconeura", "Pfeilwurz", "maranta-leuconeura",
            "Bekannt als Gebetspflanze – ihre Blätter falten sich abends zusammen wie zum Gebet.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/maranta_leuconeura.png",
            CareLevel.MEDIUM, LightRequirement.MEDIUM, true, false, "Brasilien");
        addTags(maranta, "Haustierfreundlich", "Schattenpflanze", "Tropisch");

        Plant crassula = new Plant(
            "Crassula ovata", "Geldbaum", "crassula-ovata",
            "Sukkulenter Baum mit dicken, glänzenden Blättern – soll laut Feng Shui Glück und Wohlstand bringen.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/crassula_ovata.png",
            CareLevel.EASY, LightRequirement.DIRECT, false, false, "Südafrika");
        addTags(crassula, "Anfänger", "Sukkulente", "Sonnenliebend");

        Plant nephrolepis = new Plant(
            "Nephrolepis exaltata", "Schwertfarn", "nephrolepis-exaltata",
            "Üppiger Farn mit feinen Wedeln – liebt hohe Luftfeuchtigkeit, daher perfekt fürs Badezimmer.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/nephrolepis_exaltata.png",
            CareLevel.MEDIUM, LightRequirement.MEDIUM, true, true, "Tropen weltweit");
        addTags(nephrolepis, "Haustierfreundlich", "Luftreinigend", "Schattenpflanze");

        Plant hoya = new Plant(
            "Hoya carnosa", "Wachsblume", "hoya-carnosa",
            "Rankende Pflanze mit duftenden, sternförmigen Blüten und dicken, fast künstlich wirkenden Blättern.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/hoya_carnosa.png",
            CareLevel.EASY, LightRequirement.BRIGHT, true, false, "Australien & Ostasien");
        addTags(hoya, "Haustierfreundlich", "Rankpflanze", "Blühend");

        List<Plant> plants = List.of(
            monstera, calathea, sansevieria, ficusLyrata, senecio, scindapsus,
            pilea, strelitzia, alocasia, epipremnum, zamioculcas, spathiphyllum,
            chlorophytum, aloe, ficusElastica, philodendron, maranta, crassula,
            nephrolepis, hoya
        );

        plantRepository.saveAll(plants);
        System.out.println(plants.size() + " Pflanzen geladen");
    }

    private void addTags(Plant plant, String... names) {
        for (String name : names) {
            plant.getTags().add(new Tag(name, plant));
        }
    }
}