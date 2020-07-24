package p2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import p2.backend.Beans.*;
import p2.backend.Repository.*;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(15);
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EventsRepository eventsRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    LocationRepository locationRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        //Create all the Animals
        Animal americanAlligator = new Animal("American Alligator", "Alligator Mississippiensis","The eyes and snout are positioned on the top of the head, enabling the American alligator to breathe and watch for prey, while the rest of the body is submerged.","Domestic American alligators range from long and slender to short and robust, possibly due to variations in factors such as growth rate, diet, and climate. American alligators have broad snouts, especially in captive individuals.",9,0,null);
        Animal americanBison = new Animal("American Bison", "Bison Bison","Bison are known to consume snow (or drink water) on a daily basis.","The American bison or simply bison, also commonly known as the American buffalo or simply buffalo, is a North American species of bison that once roamed the grasslands of North America in massive herds. They became nearly extinct by a combination of commercial hunting and slaughter in the 19th century and introduction of bovine diseases from domestic cattle, and have made a recent resurgence largely restricted to a few national parks and reserves.",6,0,null);
        Animal asianElephant = new Animal("Asian Elephant", "Elephas Maximus","Their trunks can carry about 4 liters of water.","Asian elephants are the largest living land animals in Asia. The largest Asian elephant ever recorded was from the Garo Hills of Assam, India; it weighed 7.7 tons, stood 11.3 ft tall at the shoulder and was 26.4 ft long from head to tail. The distinctive trunk is an elongation of the nose and upper lip combined; the nostrils are at its tip, which has a one finger-like process. The trunk contains as many as 60,000 muscles, which consist of longitudinal and radiating sets.",4,0,null);
        Animal asianOtter = new Animal("Asian Otter", "Amblonyx Cinerea","They also produce small amounts of feces, known as spraint. The spraints are important for communication among the otters; those with different smells and appearance indicate the presence of strangers.","The Asian otter is a semiaquatic mammal native to South and Southeast Asia. It is a member of the otter subfamily (Lutrinae) of the weasel family (Mustelidae), and is the smallest otter species in the world. Its paws are a distinctive feature; its claws do not extend beyond the fleshy end pads of its partially webbed fingers and toes. This gives it a high degree of manual dexterity so that it can use its paws to feed on molluscs, crabs and other small aquatic animals. The Asian small-clawed otter inhabits mangrove swamps and freshwater wetlands in South and Southeast Asia.",6,0,null);
        Animal baldEagle = new Animal("Bald Eagle", "Haliaeetus Leucocephalus","The bird has moved from Endangered to Threatened to Least Concern in the last 20 years.","The bald eagle is an opportunistic feeder which subsists mainly on fish, which it swoops down and snatches from the water with its talons. It builds the largest nest of any North American bird and the largest tree nests ever recorded for any animal species, up to 4 m (13 ft) deep, 2.5 m (8.2 ft) wide, and 1 metric ton (1.1 short tons) in weight.",5,0,null);
        Animal bobcat = new Animal("Bobcat", "Lynx Rufus","The bobcat is able to survive for long periods without food.","The bald eagle is an opportunistic feeder which subsists mainly on fish, which it swoops down and snatches from the water with its talons. It builds the largest nest of any North American bird and the largest tree nests ever recorded for any animal species, up to 4 m (13 ft) deep, 2.5 m (8.2 ft) wide, and 1 metric ton (1.1 short tons) in weight.",2,0,null);
        Animal carpetPython = new Animal("Carpet Python", "Morelia Spilotes Variegata","The snake is not venomous and kills prey by constriction.","Morelia spilota variegata is a subspecies of python found in New Guinea and Australia, smaller than the nominate subspecies Morelia spilota spilota and has a more restricted geographic range.",3,0,null);
        Animal californiaSeaLion = new Animal("California Sea Lion", "Zalophus Californicus","California sea lions have a polygynous breeding pattern. From May to August, males establish territories and try to attract females with which to mate.","The California sea lion (Zalophus californianus) is a coastal eared seal native to western North America. It is one of six species of sea lion. Its natural habitat ranges from southeast Alaska to central Mexico, including the Gulf of California. They mainly haul-out on sandy or rocky beaches, but they also frequent man-made environments such as marinas and wharves.",2,0,null);
        Animal cheetah = new Animal("Cheetah", "Acinonyx Jubatus","Cheetahs hunt by sight and not by scent and have a hunting success rate of 40-50%.","The cheetah is a large cat of the subfamily Felinae that occurs in Southern, North and East Africa, and a few localities in Iran. The species is IUCN Red Listed as vulnerable, as it suffered a substantial decline in its historic range in the 20th century due to habitat loss, poaching, illegal pet trade, and conflict with humans. By 2016, the global cheetah population has been estimated at approximately 7,100 individuals in the wild. Several African countries have taken steps to improve cheetah conservation measures.",3,0,null);
        Animal giantPanda = new Animal("Giant Panda","Ailuropoda Melanoleuca","The female pandas mostly always give birth to twins, after a period of 150 days or five months of pregnancy.","The giant panda (Ailuropoda melanoleuca, literally black and white cat-foot; Chinese: 大熊猫; pinyin: dà xióng māo, literally big bear cat), also known as panda bear or simply panda, is a bear native to south central China. It is easily recognized by the large, distinctive black patches around its eyes, over the ears, and across its round body. The name giant panda is sometimes used to distinguish it from the unrelated red panda.",9,0,null);
        Animal lion = new Animal("Lion","Panthera Leo","Lions have been kept in menageries since the time of the Roman Empire, and have been a key species sought for exhibition in zoos over the world since the late 18th century.","Lions spend much of their time resting, and are inactive for about 20 hours per day. Although lions can be active at any time, their activity generally peaks after dusk with a period of socialising, grooming, and defecating. Intermittent bursts of activity follow through the night hours until dawn, when hunting most often takes place. They spend an average of two hours a day walking, and 50 minutes eating. The lion typically inhabits grasslands and savannahs, but is absent in dense forests. It is usually more diurnal than other big cats, but when persecuted adapts to being active at night and at twilight. A lion pride consists of a few adult males, related females and cubs. Prides vary in size and composition from three to 20 adult lions, depending on habitat and prey availability. Females cooperate when hunting and prey mostly on large ungulates, including antelope, deer, buffalo, zebra and even giraffe.",2,0,null);
        Animal manedWolf = new Animal("Maned wolf","Chrysocyon Brachyurus","The maned wolf has a uniqe sound called a roar-bark that resembles both the roar of a large cat with the bark of a dog.","The maned wolf is not closely related to any other living canid. It is not a fox, wolf, coyote, dog, or jackal, but a distinct canid. The maned wolf is the tallest of the wild canids; its long legs are likely an adaptation to the tall grasslands of its native habitat. Fur of the maned wolf may be reddish brown to golden orange on the sides with long, black legs, and a distinctive black mane. The coat is marked further with a whitish tuft at the tip of the tail and a white \"bib\" beneath the throat. The mane is erectile, and typically, is used to enlarge the wolf's profile when threatened or when displaying aggression. Melanistic maned wolves do exist, but are rare. The maned wolf also is known for the distinctive odor of its territory markings, which has earned it the nickname Skunk Wolf.",2,0,null);
        Animal orangutan = new Animal("Orangutan", "Pongo Borneo","Orangutans are among the most intelligent primates. Experiments suggest they can figure out some invisiple displacement problems with a representational strategy.","Most of the day is spent feeding, resting, and travelling. They start the day feeding for 2–3 hours in the morning. They rest during midday then travel in the late afternoon. When evening arrives, they begin to prepare their nests for the night. Orangutans do not swim, although they have been recorded wading in water. The main predators of orangutans are tigers. Other predators include clouded leopards, wild dogs and crocodiles. The absence of tigers on Borneo may explain why Bornean orangutans can be found on the ground more often than their Sumatran relatives.",3,0,null);
        Animal redPanda = new Animal("Red Panda", "Ailurus Fulgens","They have a false thumb which is an extension of the wristbone.","The head and body length of a red panda measures 50 to 64 cm (20 to 25 in), and its tail is 28 to 59 cm (11 to 23 in). Males weigh 3.7 to 6.2 kg (8.2 to 13.7 lb) and females 3 to 6.0 kg (6.6 to 13.2 lb). They have long, soft, reddish-brown fur on the upper parts, blackish fur on the lower parts, and a light face with tear markings and robust cranio dental features. The light face has white badges similar to those of a raccoon, but each individual can have distinctive markings. Their roundish heads have medium-sized upright ears, black noses, and blackish eyes. Their long, bushy tails with six alternating transverse ochre rings provide balance and excellent camouflage against their habitat of moss- and lichen-covered trees. The legs are black and short with thick fur on the soles of the paws. This fur serves as thermal insulation on snow-covered or icy surfaces and conceals scent glands.",4,0,null);
        Animal tiger = new Animal("Tiger", "Panthera Tigris","Tigers are the largest species of cat, and they are endangered.","The tiger is a long-ranging species, and individuals disperse over distances of up to 650 km (400 mi) to reach tiger populations in other areas. It is strong swimmer and often bathes in ponds, lakes and rivers, thus keeping cool in the heat of the day. Among the big cats, only the jaguar shares a similar fondness for water. Individuals can cross rivers up to 7 km (4.3 mi) wide and can swim up to 29 km (18 mi) in a day.They are able to carry prey through or capture it in the water.",2,0,null);
        Animal westernLowlandGorilla = new Animal("Western Lowland Gorilla", "Gorilla Gorilla Gorilla","These gorillas are critically endangered","The tiger is a long-ranging species, and individuals disperse over distances of up to 650 km (400 mi) to reach tiger populations in other areas. It is strong swimmer and often bathes in ponds, lakes and rivers, thus keeping cool in the heat of the day. Among the big cats, only the jaguar shares a similar fondness for water. Individuals can cross rivers up to 7 km (4.3 mi) wide and can swim up to 29 km (18 mi) in a day.They are able to carry prey through or capture it in the water.",2,0,null);


        //Create all the Employee
        String password = bCryptPasswordEncoder().encode("admin");
        Employee noop = new Employee("Navroop","Hundal","NoopDog",password,0);
        Employee spencer = new Employee("Spencer","Saunders","SSaunders",password,0);
        Employee brenton = new Employee("Brenton","Poke","Bpoke",password,0);
        Employee jose = new Employee("Jose", "Soria","Jsoria",password,0);
        Employee semeon = new Employee("Semeon","Barros","A R De",password,0);
        Employee terrell = new Employee("Terrell","Moses","Tmoses",password,0);
        Employee florina= new Employee("Florina","Singson","Fsingson",password,0);

        // Create all the Events
        Events one = new Events("Breakfast with Orangutan", "Kampung Sumatra","8:00 AM - 9:30 AM");
        Events two = new Events("Elephants Bathing", "Elephant Pool", "8:45 AM - 9:00 AM");
        Events three = new Events("Short Animal Presenation", "Gaya Restaurant", "9:00 AM - 9:15 AM");
        Events four = new Events("Animal Presentation", "Kampung Sumatra Stage", "10:00 AM - 10:20 AM");
        Events five = new Events("The Exotica Wildlife Education", "Green Stage", "11:15 AM - 11:45 AM");
        Events six = new Events("Tiger Keeper Talk", "Tiger Exhibit", "11:45 AM - 12:00 PM");
        Events seven = new Events("Lemur Keeper Talk", "Aviary", "12:15 PM - 12:30 PM");
        Events eight = new Events("Animal Encounter", "Elephant View Restaurant", "1:00 PM - 2:00 PM");

        // Create all the Food
        Food bamboo = new Food("Bamboo",400,"11/27/2018","For the Pandas only");
        Food beef = new Food("Beef",600,"08/23/2018","Need to order less now");
        Food eggs = new Food("Eggs",300,"06/11/2018",null);
        Food fruits = new Food("Fruits",1000,"01/14/2018",null);
        Food hay = new Food("Hay",450,"01/14/2018",null);
        Food insects = new Food("Insects",350,"02/10/2019","Order more");
        Food rodents = new Food("Rodents",400,"04/02/2018",null);
        Food seeds = new Food("Seeds",300,"05/07/2018","Need to cancel order");
        Food treeBark = new Food("Tree Bark",250,"12/05/2018","Cancelling with dealer, pending");
        Food vegetables = new Food("Vegetables",890,"01/28/2019","Cancelling with dealer, pending");
        Food rawFish = new Food("Raw Fish",300,"04/15/2018",null);
        Food rawMeat = new Food("Raw Meat",400,"04/04/2018","Need to order more next time");
        Food carrots = new Food("carrots",200,"01/14/2019",null);


        // Create all the Locations
        Location lAmericanAlligator = new Location(38.9299392,-77.0476189,americanAlligator);
        Location lAmericanBison = new Location(38.931416, -77.052703,americanBison);
        Location lAsianElephant = new Location(38.931215, -77.050295,asianElephant);
        Location lAsianOtter = new Location(38.931103, -77.052868,asianOtter);
        Location lBaldEagle = new Location(38.930536, -77.049306,baldEagle);
        Location lBobcat = new Location(38.9299392,-77.0476189,bobcat);
        Location lCarpetPython = new Location(38.9297635,-77.0475369,carpetPython);
        Location lCaliforniaSeaLion = new Location(38.9290841,-77.0492791,californiaSeaLion);
        Location lCheetah = new Location(38.930897, -77.053289,cheetah);
        Location lGiantPanda = new Location(38.931196, -77.052790,giantPanda);
        Location lLion = new Location(38.928470, -77.046083,lion);
        Location lManedWolf = new Location(38.930575, -77.053585,manedWolf);
        Location lOrangutan = new Location(38.929801, -77.048068,orangutan);
        Location lRedPanda = new Location(38.931110, -77.052924,redPanda);
        Location lTiger = new Location(38.928793, -77.046424,tiger);
        Location lWesternLowlandGorilla = new Location(38.930138, -77.048323,westernLowlandGorilla);


        //save them all
        animalRepository.save(americanAlligator);
        animalRepository.save(americanBison);
        animalRepository.save(asianElephant);
        animalRepository.save(asianOtter);
        animalRepository.save(baldEagle);
        animalRepository.save(bobcat);
        animalRepository.save(carpetPython);
        animalRepository.save(californiaSeaLion);
        animalRepository.save(cheetah);
        animalRepository.save(giantPanda);
        animalRepository.save(lion);
        animalRepository.save(manedWolf);
        animalRepository.save(orangutan);
        animalRepository.save(redPanda);
        animalRepository.save(tiger);
        animalRepository.save(westernLowlandGorilla);

        employeeRepository.save(noop);
        employeeRepository.save(spencer);
        employeeRepository.save(brenton);
        employeeRepository.save(jose);
        employeeRepository.save(semeon);
        employeeRepository.save(terrell);
        employeeRepository.save(florina);

        eventsRepository.save(one);
        eventsRepository.save(two);
        eventsRepository.save(three);
        eventsRepository.save(four);
        eventsRepository.save(five);
        eventsRepository.save(six);
        eventsRepository.save(eight);

        foodRepository.save(bamboo);
        foodRepository.save(beef);
        foodRepository.save(eggs);
        foodRepository.save(fruits);
        foodRepository.save(hay);
        foodRepository.save(insects);
        foodRepository.save(rodents);
        foodRepository.save(seeds);
        eventsRepository.save(seven);
        foodRepository.save(treeBark);
        foodRepository.save(vegetables);
        foodRepository.save(rawFish);
        foodRepository.save(rawMeat);
        foodRepository.save(carrots);


        locationRepository.save(lAmericanAlligator);
        locationRepository.save(lAmericanBison);
        locationRepository.save(lAsianElephant);
        locationRepository.save(lAsianOtter);
        locationRepository.save(lBaldEagle);
        locationRepository.save(lBobcat);
        locationRepository.save(lCarpetPython);
        locationRepository.save(lCaliforniaSeaLion);
        locationRepository.save(lCheetah);
        locationRepository.save(lGiantPanda);
        locationRepository.save(lLion);
        locationRepository.save(lManedWolf);
        locationRepository.save(lOrangutan);
        locationRepository.save(lRedPanda);
        locationRepository.save(lTiger);
        locationRepository.save(lWesternLowlandGorilla);


        //Create links between everything
        // food animal link
        Set<Food> food = new HashSet<>();
        food.add(rawFish);
        food.add(beef);

        lion.setFood(food);
        cheetah.setFood(food);

        Set<Animal> animals = new HashSet<>();
        animals.add(lion);
        animals.add(cheetah);

        rawFish.setAnimalFood(animals);
        beef.setAnimalFood(animals);

        animalRepository.save(lion);
        animalRepository.save(cheetah);
        foodRepository.save(rawFish);
        foodRepository.save(beef);

        // Animal to Employee
        Set<Animal> animalsToEmployee = new HashSet<>();

        animalsToEmployee.add(giantPanda);

        jose.setAnimals(animalsToEmployee);
        florina.setAnimals(animalsToEmployee);

        employeeRepository.save(jose);
        employeeRepository.save(florina);
    }
}


