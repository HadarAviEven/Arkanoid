//        ClassLoader.getSystemClassLoader().getResourcesAsStream("Adfadf");
//        System.out.println(childsOfResources[2].getName());
//        System.out.println(deepFiles[0].getName());
//        System.out.println(deepFiles.length);
//        childsOfResource[0].listFile();
//        System.out.println("absulote path: " + fileParent.getAbsolutePath());
//        System.out.println("size: " + fileParent.list().length);
//        fr = new FileReader("resources/definitions/moon_level_definition.txt");


//        List<LevelInformation> list = null;
//        File fileParent = new File("resources");
//        File[] childsOfResources = fileParent.listFiles();
//        File definitions = childsOfResources[2];
//        BufferedReader br = null;
//        FileReader fr = null;
//        fr = new FileReader(definitions);
//        br = new BufferedReader(fr);
//        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
//        list = levelSpecificationReader.fromReader(br);
//        br.close();
//        fr.close();


//        List<LevelInformation> list = new ArrayList<LevelInformation>();
//        LevelInformation newLevel = null;//
//        File fileParent = new File("resources");
//        File[] childsOfResources = fileParent.listFiles();
//        File[] deepFiles = childsOfResources[2].listFiles();
//        for (int i = 0; i < deepFiles.length; i++) {
//        BufferedReader br = null;
//        FileReader fr = null;
//        if (deepFiles[i].getName().contains("level")) {
//            fr = new FileReader(deepFiles[i].getName());
//            br = new BufferedReader(fr);
////            LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
//            LevelFromFile levelFromFile = new LevelFromFile();//
////            list = levelSpecificationReader.fromReader(br);
//            newLevel = levelFromFile.fromReader(br);
////            br.close();
////            fr.close();
//        }
//        list.add(newLevel);//
//    }

