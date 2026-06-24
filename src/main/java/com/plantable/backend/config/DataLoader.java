package com.plantable.backend.config;

import com.plantable.backend.model.*;
import com.plantable.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserPlantRepository userPlantRepository;

    public DataLoader(PlantRepository plantRepository, UserRepository userRepository,
                      PostRepository postRepository, CommentRepository commentRepository,
                      UserPlantRepository userPlantRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userPlantRepository = userPlantRepository;
    }

    @Override
    public void run(String... args) {
        if (plantRepository.count() > 0) {
            return;
        }

        // Create admin and test users
        User admin = new User("auth0|6a3bab58bda5fa3340b498e4", "matosnico02@gmail.com", "Admin User");
        admin.setIsAdmin(true);
        userRepository.save(admin);

        User testUser1 = new User("auth0|user123", "user1@plantable.local", "Max Grün");
        User testUser2 = new User("auth0|user456", "user2@plantable.local", "Emma Schmidt");
        userRepository.save(testUser1);
        userRepository.save(testUser2);

        Plant monstera = new Plant(
            "Monstera deliciosa", "Fensterblatt", "monstera-deliciosa",
            "Ein lebendiges Kunstwerk – mit ihren großen, gefensterten Blättern bringt sie tropisches Flair in jeden Raum.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/monstera_deliciosa.png",
            CareLevel.EASY, LightRequirement.BRIGHT, false, true, "Mittelamerika");
        addTags(monstera, "Anfänger", "Luftreinigend", "Tropisch", "Zimmerpflanze");
        setIntervals(monstera, 7, 3, 21, 30, 365, 180, 90);
        monstera.setPrice(34.99);

        Plant calathea = new Plant(
            "Calathea orbifolia", "Korbmarante", "calathea-orbifolia",
            "Mit ihren großen, runden Blättern und silbrigen Streifen ist sie eine echte Schönheit für schattigere Plätze.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/calathea_orbifolia.png",
            CareLevel.MEDIUM, LightRequirement.MEDIUM, true, false, "Bolivien");
        addTags(calathea, "Haustierfreundlich", "Schattenpflanze", "Tropisch");
        setIntervals(calathea, 5, 2, 14, null, 365, null, 90);
        calathea.setPrice(29.99);

        Plant sansevieria = new Plant(
            "Sansevieria laurentii", "Bogenhanf", "sansevieria-laurentii",
            "Robust und pflegeleicht, perfekt für Anfänger. Filtert die Luft besonders effektiv.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/sansevieria_laurentii.png",
            CareLevel.EASY, LightRequirement.LOW, false, true, "Westafrika");
        addTags(sansevieria, "Anfänger", "Luftreinigend", "Schattenpflanze");
        setIntervals(sansevieria, 21, null, 60, 30, 730, null, 90);
        sansevieria.setPrice(19.99);

        Plant ficusLyrata = new Plant(
            "Ficus lyrata", "Geigenblattfeige", "ficus-lyrata",
            "Imposant und elegant – die Geigenblattfeige ist ein Statement-Piece für jeden hellen Raum.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/ficus_lyrata.png",
            CareLevel.HARD, LightRequirement.BRIGHT, false, true, "Westafrika");
        addTags(ficusLyrata, "Experte", "Luftreinigend", "Zimmerpflanze");
        setIntervals(ficusLyrata, 7, 7, 21, 14, 365, 365, 90);
        ficusLyrata.setPrice(59.99);

        Plant senecio = new Plant(
            "Senecio rowleyanus", "Erbsenpflanze", "senecio-rowleyanus",
            "Hängende Sukkulente mit perlenartigen Blättern – ein verspielter Hingucker für Regale und Ampeln.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/senecio_rowleyanus.png",
            CareLevel.MEDIUM, LightRequirement.DIRECT, false, false, "Südwestafrika");
        addTags(senecio, "Sukkulente", "Hängepflanze", "Sonnenliebend");
        setIntervals(senecio, 14, null, 30, null, 730, null, 90);
        senecio.setPrice(12.99);

        Plant scindapsus = new Plant(
            "Scindapsus pictus", "Silberblatt-Efeutute", "scindapsus-pictus",
            "Rankende Schönheit mit silbrig gemusterten Blättern – wächst in fast jeder Lichtsituation.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/scindapsus_pictus.png",
            CareLevel.EASY, LightRequirement.MEDIUM, true, true, "Südostasien");
        addTags(scindapsus, "Anfänger", "Rankpflanze", "Haustierfreundlich", "Luftreinigend");
        setIntervals(scindapsus, 7, 5, 30, 30, 365, 90, 90);
        scindapsus.setPrice(14.99);

        Plant pilea = new Plant(
            "Pilea peperomioides", "Ufopflanze", "pilea-peperomioides",
            "Mit ihren runden, münzenförmigen Blättern ein echter Trendliebling. Sehr einfach zu vermehren.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/pilea_peperomioides.png",
            CareLevel.EASY, LightRequirement.BRIGHT, true, false, "China");
        addTags(pilea, "Anfänger", "Haustierfreundlich", "Zimmerpflanze");
        setIntervals(pilea, 7, null, 21, null, 365, null, 90);
        pilea.setPrice(11.99);

        Plant strelitzia = new Plant(
            "Strelitzia nicolai", "Weiße Paradiesvogelblume", "strelitzia-nicolai",
            "Bringt mit ihren bananenartigen Blättern tropisches Flair ins Wohnzimmer und kann beeindruckende Größen erreichen.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/strelitzia_nicolai.png",
            CareLevel.MEDIUM, LightRequirement.BRIGHT, false, false, "Südafrika");
        addTags(strelitzia, "Tropisch", "Sonnenliebend", "Zimmerpflanze");
        setIntervals(strelitzia, 7, null, 21, 30, 365, null, 90);
        strelitzia.setPrice(44.99);

        Plant alocasia = new Plant(
            "Alocasia polly", "Pfeilblatt", "alocasia-polly",
            "Dramatische pfeilförmige Blätter mit silbrigen Adern – ein Hingucker, der etwas Aufmerksamkeit braucht.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/alocasia_polly.png",
            CareLevel.HARD, LightRequirement.BRIGHT, false, false, "Südostasien");
        addTags(alocasia, "Experte", "Tropisch");
        setIntervals(alocasia, 5, 2, 14, 14, 365, null, 90);
        alocasia.setPrice(39.99);

        Plant epipremnum = new Plant(
            "Epipremnum aureum", "Goldene Efeutute", "epipremnum-aureum",
            "Die wohl pflegeleichteste Rankpflanze – wächst auch bei wenig Licht und vergibt Pflegefehler.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/epipremnum_aureum.png",
            CareLevel.EASY, LightRequirement.LOW, false, true, "Französisch-Polynesien");
        addTags(epipremnum, "Anfänger", "Rankpflanze", "Luftreinigend", "Schattenpflanze");
        setIntervals(epipremnum, 7, null, 30, null, 365, 90, 90);
        epipremnum.setPrice(9.99);

        Plant zamioculcas = new Plant(
            "Zamioculcas zamiifolia", "Glücksfeder", "zamioculcas-zamiifolia",
            "Nahezu unzerstörbar – verzeiht Vergessen beim Gießen und gedeiht auch in dunklen Ecken.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/zamioculcas_zamiifolia.png",
            CareLevel.EASY, LightRequirement.LOW, false, true, "Ostafrika");
        addTags(zamioculcas, "Anfänger", "Luftreinigend", "Schattenpflanze");
        setIntervals(zamioculcas, 21, null, 60, 30, 730, null, 90);
        zamioculcas.setPrice(24.99);

        Plant spathiphyllum = new Plant(
            "Spathiphyllum wallisii", "Einblatt", "spathiphyllum-wallisii",
            "Elegante weiße Blütenscheiden auf glänzend grünem Laub – signalisiert klar, wann sie Wasser braucht.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/spathiphyllum_wallisii.png",
            CareLevel.EASY, LightRequirement.MEDIUM, false, true, "Mittel- und Südamerika");
        addTags(spathiphyllum, "Anfänger", "Luftreinigend", "Blühend");
        setIntervals(spathiphyllum, 7, 5, 21, 14, 365, 180, 90);
        spathiphyllum.setPrice(17.99);

        Plant chlorophytum = new Plant(
            "Chlorophytum comosum", "Grünlilie", "chlorophytum-comosum",
            "Klassiker der Zimmerpflanzen mit hängenden Kindeln – ideal für Anfänger und absolut haustierfreundlich.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/chlorophytum_comosum.png",
            CareLevel.EASY, LightRequirement.BRIGHT, true, true, "Südafrika");
        addTags(chlorophytum, "Anfänger", "Haustierfreundlich", "Luftreinigend", "Hängepflanze");
        setIntervals(chlorophytum, 7, null, 21, null, 365, 180, 90);
        chlorophytum.setPrice(8.99);

        Plant aloe = new Plant(
            "Aloe vera", "Echte Aloe", "aloe-vera",
            "Sukkulente Heilpflanze mit fleischigen Blättern – braucht viel Sonne und wenig Wasser.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/aloe_vera.png",
            CareLevel.EASY, LightRequirement.DIRECT, false, false, "Arabische Halbinsel");
        addTags(aloe, "Anfänger", "Sukkulente", "Sonnenliebend", "Heilpflanze");
        setIntervals(aloe, 14, null, 60, null, 730, null, 90);
        aloe.setPrice(7.99);

        Plant ficusElastica = new Plant(
            "Ficus elastica", "Gummibaum", "ficus-elastica",
            "Robuster Klassiker mit ledrigen, glänzenden Blättern – wächst über Jahre zu einem stattlichen Baum heran.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/ficus_elastica.png",
            CareLevel.EASY, LightRequirement.BRIGHT, false, true, "Südostasien");
        addTags(ficusElastica, "Anfänger", "Luftreinigend", "Zimmerpflanze");
        setIntervals(ficusElastica, 7, null, 21, 14, 365, 365, 90);
        ficusElastica.setPrice(27.99);

        Plant philodendron = new Plant(
            "Philodendron scandens", "Herzblatt-Philodendron", "philodendron-scandens",
            "Schnellwachsende Kletterpflanze mit herzförmigen Blättern – flexibel als Hängepflanze oder am Moosstab.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/philodendron_scandens.png",
            CareLevel.EASY, LightRequirement.MEDIUM, false, true, "Mittelamerika");
        addTags(philodendron, "Anfänger", "Rankpflanze", "Luftreinigend", "Tropisch");
        setIntervals(philodendron, 7, 5, 21, null, 365, 90, 90);
        philodendron.setPrice(16.99);

        Plant maranta = new Plant(
            "Maranta leuconeura", "Pfeilwurz", "maranta-leuconeura",
            "Bekannt als Gebetspflanze – ihre Blätter falten sich abends zusammen wie zum Gebet.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/maranta_leuconeura.png",
            CareLevel.MEDIUM, LightRequirement.MEDIUM, true, false, "Brasilien");
        addTags(maranta, "Haustierfreundlich", "Schattenpflanze", "Tropisch");
        setIntervals(maranta, 5, 3, 14, null, 365, null, 90);
        maranta.setPrice(18.99);

        Plant crassula = new Plant(
            "Crassula ovata", "Geldbaum", "crassula-ovata",
            "Sukkulenter Baum mit dicken, glänzenden Blättern – soll laut Feng Shui Glück und Wohlstand bringen.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/crassula_ovata.png",
            CareLevel.EASY, LightRequirement.DIRECT, false, false, "Südafrika");
        addTags(crassula, "Anfänger", "Sukkulente", "Sonnenliebend");
        setIntervals(crassula, 14, null, 60, null, 730, null, 90);
        crassula.setPrice(13.99);

        Plant nephrolepis = new Plant(
            "Nephrolepis exaltata", "Schwertfarn", "nephrolepis-exaltata",
            "Üppiger Farn mit feinen Wedeln – liebt hohe Luftfeuchtigkeit, daher perfekt fürs Badezimmer.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/nephrolepis_exaltata.png",
            CareLevel.MEDIUM, LightRequirement.MEDIUM, true, true, "Tropen weltweit");
        addTags(nephrolepis, "Haustierfreundlich", "Luftreinigend", "Schattenpflanze");
        setIntervals(nephrolepis, 3, 2, 14, null, 365, 180, 90);
        nephrolepis.setPrice(15.99);

        Plant hoya = new Plant(
            "Hoya carnosa", "Wachsblume", "hoya-carnosa",
            "Rankende Pflanze mit duftenden, sternförmigen Blüten und dicken, fast künstlich wirkenden Blättern.",
            "https://raw.githubusercontent.com/htwg-in-schneider/frontend-static-plantable/main/images/hoya_carnosa.png",
            CareLevel.EASY, LightRequirement.BRIGHT, true, false, "Australien & Ostasien");
        addTags(hoya, "Haustierfreundlich", "Rankpflanze", "Blühend");
        setIntervals(hoya, 7, null, 30, null, 730, 365, 90);
        hoya.setPrice(22.99);

        List<Plant> plants = List.of(
            monstera, calathea, sansevieria, ficusLyrata, senecio, scindapsus,
            pilea, strelitzia, alocasia, epipremnum, zamioculcas, spathiphyllum,
            chlorophytum, aloe, ficusElastica, philodendron, maranta, crassula,
            nephrolepis, hoya
        );

        plantRepository.saveAll(plants);
        System.out.println(plants.size() + " Pflanzen geladen");

        // Create community posts
        Post post1 = new Post(
            "Tipps zur Pflege von Monstera",
            "Meine Monstera wächst prächtig! Hier sind meine besten Tipps:\n\n1. Helles, indirektes Licht ist ideal\n2. Gießen, wenn die oberste Erdschicht trocken ist\n3. Jeden Monat mit Langzeitdünger versorgen\n4. Moosstock hilft ihr hochzuwachsen\n\nWelche Erfahrungen habt ihr gemacht?",
            testUser1
        );
        post1.setTags(List.of("Monstera", "Pflanzenpflege", "Anfänger-Tipps"));
        postRepository.save(post1);

        Post post2 = new Post(
            "Habt ihr schonmal Stecklinge vermehrt?",
            "Ich bin begeistert von der Vermehrung über Stecklinge! Es ist so einfach und man kann seine Pflanzensammlung schnell vergrößern. \n\nBeste Erfahrungen habe ich mit Philodendron und Efeutute gemacht. Bei welchen Pflanzen habt ihr schon erfolgreich Stecklinge gezogen?",
            testUser2
        );
        post2.setTags(List.of("Vermehrung", "Stecklinge", "Tipps"));
        postRepository.save(post2);

        // Create comments
        Comment comment1 = new Comment(
            "Danke für die Tipps! Ich habe das gerade ausprobiert und es funktioniert wirklich gut.",
            testUser2,
            post1
        );
        commentRepository.save(comment1);

        Comment comment2 = new Comment(
            "Bei mir klappt die Vermehrung über Stecklinge auch sehr gut. Braucht einfach nur Geduld!",
            testUser1,
            post2
        );
        commentRepository.save(comment2);

        System.out.println("Community-Daten geladen: 1 Admin-User, 2 Test-User, 2 Posts, 2 Kommentare");

        // ── Admin-Testdaten: Pflanzen mit fälligen und überfälligen Aufgaben ──
        LocalDate today = LocalDate.now();

        // Monty (Monstera) – seit 30 Tagen, mehrere überfällige Aufgaben
        UserPlant monty = new UserPlant();
        monty.setUser(admin);
        monty.setPlant(monstera);
        monty.setNickname("Monty");
        monty.setAcquiredAt(today.minusDays(30));
        monty.setWateringIntervalDays(7);
        monty.setLastWateredAt(today.minusDays(10));     // fällig: heute-3  → OVERDUE
        monty.setMistingIntervalDays(3);
        monty.setLastMistedAt(today.minusDays(5));       // fällig: heute-2  → OVERDUE
        monty.setFertilizingIntervalDays(21);
        // lastFertilizedAt = null → base = acquiredAt (heute-30), fällig: heute-9 → OVERDUE
        monty.setLeafCleaningIntervalDays(30);
        // lastLeafCleanedAt = null → base = acquiredAt (heute-30), fällig: heute   → DUE
        monty.setRepottingIntervalDays(365);             // fällig: heute+335 → UPCOMING
        monty.setPruningIntervalDays(180);               // fällig: heute+150 → UPCOMING
        monty.setPestCheckIntervalDays(90);              // fällig: heute+60  → UPCOMING
        userPlantRepository.save(monty);

        // Pili (Pilea) – seit 7 Tagen, Gießen heute fällig
        UserPlant pili = new UserPlant();
        pili.setUser(admin);
        pili.setPlant(pilea);
        pili.setNickname("Pili");
        pili.setAcquiredAt(today.minusDays(7));
        pili.setWateringIntervalDays(7);
        // lastWateredAt = null → base = acquiredAt (heute-7), fällig: heute → DUE
        pili.setFertilizingIntervalDays(21);             // fällig: heute+14  → UPCOMING
        pili.setRepottingIntervalDays(365);              // fällig: heute+358 → UPCOMING
        pili.setPestCheckIntervalDays(90);               // fällig: heute+83  → UPCOMING
        userPlantRepository.save(pili);

        // Sanse (Bogenhanf) – kürzlich hinzugefügt, alles demnächst
        UserPlant sanse = new UserPlant();
        sanse.setUser(admin);
        sanse.setPlant(sansevieria);
        sanse.setNickname("Sanse");
        sanse.setAcquiredAt(today.minusDays(3));
        sanse.setWateringIntervalDays(21);               // fällig: heute+18  → UPCOMING
        sanse.setFertilizingIntervalDays(60);            // fällig: heute+57  → UPCOMING
        sanse.setLeafCleaningIntervalDays(30);           // fällig: heute+27  → UPCOMING
        sanse.setRepottingIntervalDays(730);             // fällig: heute+727 → UPCOMING
        sanse.setPestCheckIntervalDays(90);              // fällig: heute+87  → UPCOMING
        userPlantRepository.save(sanse);

        System.out.println("Admin-Testpflanzen geladen: Monty (OVERDUE/DUE), Pili (DUE), Sanse (UPCOMING)");
    }

    private void addTags(Plant plant, String... names) {
        for (String name : names) {
            plant.getTags().add(new Tag(name, plant));
        }
    }

    private void setIntervals(Plant plant,
                              Integer watering, Integer misting, Integer fertilizing,
                              Integer leafCleaning, Integer repotting, Integer pruning,
                              Integer pestCheck) {
        plant.setDefaultWateringIntervalDays(watering);
        plant.setDefaultMistingIntervalDays(misting);
        plant.setDefaultFertilizingIntervalDays(fertilizing);
        plant.setDefaultLeafCleaningIntervalDays(leafCleaning);
        plant.setDefaultRepottingIntervalDays(repotting);
        plant.setDefaultPruningIntervalDays(pruning);
        plant.setDefaultPestCheckIntervalDays(pestCheck);
    }
}