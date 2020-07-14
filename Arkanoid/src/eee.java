//      LevelFromFile levelFromFile = new LevelFromFile();
//      LevelInformation level = levelFromFile.fromReader(reader);
//      this.levelInformationList.add(level);
//      return this.levelInformationList;
//  }


//      File[] deepFiles = reader.listFiles();
//      for (int i = 0; i < deepFiles.length; i++) {
//          BufferedReader br = null;
//          FileReader fr = null;
//          if (deepFiles[i].getName().contains("level")) {
//              fr = new FileReader(deepFiles[i].getName());
//              br = new BufferedReader(fr);
//              reader = br;
//          }
//      }