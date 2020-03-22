

import 'package:flutter/material.dart';

class CreateCaseNotifier extends ChangeNotifier {

  bool submitted = false;
  String location = "";
  DateTime date = DateTime.now();
  
  void setLocation(String location){
    if(!submitted){
      this.location = location;
      notifyListeners();
    }
  }

  void setDate(DateTime date){
    if(!submitted){
      this.date = date;
      notifyListeners();
    }
  }

  void submit(){
    if(!submitted){
      submitted = true;
      //TODO Submit here
    }
  }

}