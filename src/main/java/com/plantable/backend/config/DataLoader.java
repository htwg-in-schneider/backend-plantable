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
        commentRepository.deleteAll();
        userPlantRepository.deleteAll();
        postRepository.deleteAll();
        plantRepository.deleteAll();
        userRepository.deleteAll();

        User admin = createUser("auth0|6a3bab58bda5fa3340b498e4", "matosnico02@gmail.com", "Admin User", true);
        User testUser1 = createUser("auth0|user123", "user1@plantable.local", "Max Grün", false);
        User testUser2 = createUser("auth0|user456", "user2@plantable.local", "Emma Schmidt", false);
        // auth0Id muss nach Auth0-Registrierung mit der echten ID ersetzt werden
        User plantableUser = createUser("auth0|user789", "User@plantable.com", "Plantable User", false);

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

        monstera = plantRepository.save(monstera);
        calathea = plantRepository.save(calathea);
        sansevieria = plantRepository.save(sansevieria);
        ficusLyrata = plantRepository.save(ficusLyrata);
        senecio = plantRepository.save(senecio);
        scindapsus = plantRepository.save(scindapsus);
        pilea = plantRepository.save(pilea);
        strelitzia = plantRepository.save(strelitzia);
        alocasia = plantRepository.save(alocasia);
        epipremnum = plantRepository.save(epipremnum);
        zamioculcas = plantRepository.save(zamioculcas);
        spathiphyllum = plantRepository.save(spathiphyllum);
        chlorophytum = plantRepository.save(chlorophytum);
        aloe = plantRepository.save(aloe);
        ficusElastica = plantRepository.save(ficusElastica);
        philodendron = plantRepository.save(philodendron);
        maranta = plantRepository.save(maranta);
        crassula = plantRepository.save(crassula);
        nephrolepis = plantRepository.save(nephrolepis);
        hoya = plantRepository.save(hoya);
        System.out.println("20 Pflanzen geladen");

        {
            Post post1 = new Post(
                "Tipps zur Pflege von Monstera",
                "Meine Monstera wächst prächtig! Hier sind meine besten Tipps:\n\n1. Helles, indirektes Licht ist ideal\n2. Gießen, wenn die oberste Erdschicht trocken ist\n3. Jeden Monat mit Langzeitdünger versorgen\n4. Moosstock hilft ihr hochzuwachsen\n\nWelche Erfahrungen habt ihr gemacht?",
                testUser1
            );
            post1.setTags(List.of("Monstera", "Pflanzenpflege", "Anfänger-Tipps"));
            postRepository.save(post1);
            commentRepository.save(new Comment("Danke für die Tipps! Meine Monstera hat endlich neue Blätter getrieben.", testUser2, post1));
            commentRepository.save(new Comment("Den Moosstock kann ich auch nur empfehlen – macht wirklich einen Unterschied!", plantableUser, post1));

            Post post2 = new Post(
                "Habt ihr schonmal Stecklinge vermehrt?",
                "Ich bin begeistert von der Vermehrung über Stecklinge! Es ist so einfach und man kann seine Pflanzensammlung schnell vergrößern.\n\nBeste Erfahrungen habe ich mit Philodendron und Efeutute gemacht. Bei welchen Pflanzen habt ihr schon erfolgreich Stecklinge gezogen?",
                testUser2
            );
            post2.setTags(List.of("Vermehrung", "Stecklinge", "Tipps"));
            postRepository.save(post2);
            commentRepository.save(new Comment("Bei mir klappt die Vermehrung über Stecklinge auch sehr gut. Braucht einfach nur Geduld!", testUser1, post2));

            Post post3 = new Post(
                "Meine Calathea lässt die Blätter hängen – was tun?",
                "Ich habe seit zwei Wochen eine Calathea orbifolia und die Blätter hängen immer mehr nach unten. Ich gieße sie alle 5 Tage und stelle sie ans Nordfenster.\n\nHat jemand eine Idee woran das liegen könnte? Zu wenig Licht? Zu viel Wasser?",
                plantableUser
            );
            post3.setTags(List.of("Calathea", "Hilfe", "Pflanzenpflege"));
            postRepository.save(post3);
            commentRepository.save(new Comment("Calatheen mögen keine Staunässe – lass die Erde zwischen den Wassergaben etwas antrocknen. Nordfenster ist leider auch zu dunkel.", testUser1, post3));
            commentRepository.save(new Comment("Ich hatte dasselbe Problem. Habe sie ans Ostfenster gestellt und seitdem erholt sie sich super.", testUser2, post3));

            Post post4 = new Post(
                "Welche Pflanzen eignen sich fürs Badezimmer?",
                "Unser Badezimmer hat nur ein kleines Fenster und ist recht feucht. Ich suche Pflanzen, die dort trotzdem gedeihen.\n\nHabe schon Farn und Efeutute im Kopf – habt ihr noch andere Ideen?",
                testUser2
            );
            post4.setTags(List.of("Badezimmer", "Anfänger", "Schattenpflanzen"));
            postRepository.save(post4);
            commentRepository.save(new Comment("Schwertfarn (Nephrolepis) ist perfekt fürs Bad – liebt die hohe Luftfeuchtigkeit!", plantableUser, post4));

            Post post5 = new Post(
                "Frühjahrsumtopfen – meine Erfahrungen",
                "Dieses Jahr habe ich zum ersten Mal alle meine Pflanzen im Frühling umgetopft und der Unterschied ist enorm!\n\nMeine Tipps:\n- Frische Erde mit Perlite mischen für bessere Drainage\n- Topf nur eine Größe größer wählen\n- Nach dem Umtopfen 1-2 Wochen nicht düngen\n\nHat jemand noch Ergänzungen?",
                testUser1
            );
            post5.setTags(List.of("Umtopfen", "Frühjahr", "Pflanzenpflege", "Tipps"));
            postRepository.save(post5);
            commentRepository.save(new Comment("Sehr hilfreiche Zusammenfassung! Ich füge noch hinzu: bei empfindlichen Pflanzen die Wurzeln kurz abspülen, um alte Erde zu entfernen.", testUser2, post5));

            System.out.println("Community-Daten geladen: 5 Posts, 8 Kommentare");
        }

        {
            LocalDate today = LocalDate.now();

            UserPlant monty = new UserPlant();
            monty.setUser(admin);
            monty.setPlant(monstera);
            monty.setNickname("Monty");
            monty.setAcquiredAt(today.minusDays(30));
            monty.setWateringIntervalDays(7);
            monty.setLastWateredAt(today.minusDays(10));
            monty.setMistingIntervalDays(3);
            monty.setLastMistedAt(today.minusDays(5));
            monty.setFertilizingIntervalDays(21);
            monty.setLeafCleaningIntervalDays(30);
            monty.setRepottingIntervalDays(365);
            monty.setPruningIntervalDays(180);
            monty.setPestCheckIntervalDays(90);
            userPlantRepository.save(monty);

            UserPlant pili = new UserPlant();
            pili.setUser(admin);
            pili.setPlant(pilea);
            pili.setNickname("Pili");
            pili.setAcquiredAt(today.minusDays(7));
            pili.setWateringIntervalDays(7);
            pili.setFertilizingIntervalDays(21);
            pili.setRepottingIntervalDays(365);
            pili.setPestCheckIntervalDays(90);
            userPlantRepository.save(pili);

            UserPlant sanse = new UserPlant();
            sanse.setUser(admin);
            sanse.setPlant(sansevieria);
            sanse.setNickname("Sanse");
            sanse.setAcquiredAt(today.minusDays(3));
            sanse.setWateringIntervalDays(21);
            sanse.setFertilizingIntervalDays(60);
            sanse.setLeafCleaningIntervalDays(30);
            sanse.setRepottingIntervalDays(730);
            sanse.setPestCheckIntervalDays(90);
            userPlantRepository.save(sanse);

            System.out.println("Admin-Testpflanzen geladen: Monty, Pili, Sanse");
        }

        {
            LocalDate today = LocalDate.now();

            // Calathea – Gießen & Besprühen fällig
            UserPlant cala = new UserPlant();
            cala.setUser(plantableUser);
            cala.setPlant(calathea);
            cala.setNickname("Cala");
            cala.setAcquiredAt(today.minusDays(20));
            cala.setWateringIntervalDays(5);
            cala.setLastWateredAt(today.minusDays(6));
            cala.setMistingIntervalDays(2);
            cala.setLastMistedAt(today.minusDays(4));
            cala.setFertilizingIntervalDays(14);
            cala.setRepottingIntervalDays(365);
            cala.setPestCheckIntervalDays(90);
            userPlantRepository.save(cala);

            // Ficus lyrata – Gießen & Blätter putzen fällig
            UserPlant ficus = new UserPlant();
            ficus.setUser(plantableUser);
            ficus.setPlant(ficusLyrata);
            ficus.setNickname("Figaro");
            ficus.setAcquiredAt(today.minusDays(60));
            ficus.setWateringIntervalDays(7);
            ficus.setLastWateredAt(today.minusDays(9));
            ficus.setFertilizingIntervalDays(21);
            ficus.setLastFertilizedAt(today.minusDays(22));
            ficus.setLeafCleaningIntervalDays(14);
            ficus.setLastLeafCleanedAt(today.minusDays(16));
            ficus.setRepottingIntervalDays(365);
            ficus.setPestCheckIntervalDays(90);
            userPlantRepository.save(ficus);

            // Nephrolepis – alle Pflegeaufgaben fällig
            UserPlant farn = new UserPlant();
            farn.setUser(plantableUser);
            farn.setPlant(nephrolepis);
            farn.setNickname("Ferdi");
            farn.setAcquiredAt(today.minusDays(14));
            farn.setWateringIntervalDays(3);
            farn.setLastWateredAt(today.minusDays(4));
            farn.setMistingIntervalDays(2);
            farn.setLastMistedAt(today.minusDays(3));
            farn.setFertilizingIntervalDays(14);
            farn.setRepottingIntervalDays(365);
            farn.setPestCheckIntervalDays(90);
            userPlantRepository.save(farn);

            System.out.println("Plantable-User-Pflanzen geladen: Cala, Figaro, Ferdi");
        }
    }

    private User createUser(String auth0Id, String email, String name, boolean isAdmin) {
        User u = new User(auth0Id, email, name);
        if (isAdmin) u.setIsAdmin(true);
        return userRepository.save(u);
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