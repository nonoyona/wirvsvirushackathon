import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:sick_or_not_admin/SOSHost.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';

class LoginNotifier extends ChangeNotifier {
  String username;
  String password;
  bool submitted = false;
  bool failed = false;
  final AuthNotifier auth;

  LoginNotifier({@required this.auth});

  bool get submittedAndNotFailed => submitted && !failed;



  void setPassword(String password) {
    if (!submitted || failed) {
      this.password = password;
      failed = false;
      submitted = false;
      notifyListeners();
    }
  }

  void setUsername(String username) {
    if (!submitted || failed) {
      this.username = username;
      failed = false;
      submitted = false;
      notifyListeners();
    }
  }

  void submit() async {
    submitted = true;
    notifyListeners();
    if (username == null ||
        username.isEmpty ||
        password == null ||
        password.isEmpty) {
      failed = true;
      notifyListeners();
      return;
    }
    //http://api.sick-or-not.dev.schaefkn.com
    var response = await http.post("$host/login",
        body: jsonEncode({
          "username": username,
          "password": password,
        }));
    var json = jsonDecode(response.body);
    if (response.statusCode == 200) {
      var token = json["token"] as String;
      if (token == null) {
        failed = true;
        notifyListeners();
        return;
      }else{
        auth.setJWT(token);
      }
    } else {
      failed = true;
      notifyListeners();
      return;
    }
  }
}
