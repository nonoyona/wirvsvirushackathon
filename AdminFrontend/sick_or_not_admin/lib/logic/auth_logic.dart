import 'package:flutter/material.dart';

class AuthNotifier extends ChangeNotifier {
  String jsonWebToken = "";

  bool get loggedIn => jsonWebToken.isNotEmpty;

  void setJWT(String token) {
    this.jsonWebToken = token;
    notifyListeners();
  }

  void logOut() {
    this.jsonWebToken = "";
    notifyListeners();
  }
}
