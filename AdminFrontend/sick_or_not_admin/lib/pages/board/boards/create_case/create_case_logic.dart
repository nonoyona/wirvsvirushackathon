import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/SOSHost.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';

class CreateCaseNotifier extends ChangeNotifier {
  bool submitted = false;
  bool received = false;
  String id = "";
  String location = "";
  DateTime date = DateTime.now();
  final AuthNotifier auth;
  final DashboardNotifier dashboard;

  CreateCaseNotifier(this.auth, this.dashboard);

  void setLocation(String location) {
    if (!submitted || received) {
      this.location = location;
      submitted = false;
      received = false;
      notifyListeners();
    }
  }

  void setDate(DateTime date) {
    if (!submitted || received) {
      this.date = date;
      submitted = false;
      received = false;
      notifyListeners();
    }
  }

  void submit() async {
    if (!submitted) {
      submitted = true;
      Map<String, String> requestHeaders = {
      'Content-type': 'application/json',
      'Authorization': '${auth.jsonWebToken}'
    };
      var value = await http.post(
        "$host/create",
        headers: requestHeaders,
        body: jsonEncode(
          {
            "date": date.millisecondsSinceEpoch,
            "location": location,
          },
        ),
      );
      if (value.statusCode == 200) {
        var object = (jsonDecode(value.body)["id"] as String) ?? "";
        received = true;
        this.id = object;
        dashboard.refresh();
        notifyListeners();
      } else {
        auth.logOut();
      }
    }
  }
}
