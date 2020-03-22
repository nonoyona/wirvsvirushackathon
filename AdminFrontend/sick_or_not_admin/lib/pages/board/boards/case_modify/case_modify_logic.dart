import 'dart:convert';

import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/SOSHost.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';
import 'package:sick_or_not_admin/pages/board/logic/board_logic.dart';
import 'package:http/http.dart' as http;

class CaseModificationNotifier extends ChangeNotifier {
  final String id;
  final String date;
  final String location;
  TestResult result;
  bool submitted = false;
  bool received = false;
  final BoardNotifier boards;
  final AuthNotifier auth;
  final DashboardNotifier dashboard;

  CaseModificationNotifier({
    this.dashboard,
    this.boards,
    this.auth,
    this.id,
    this.date,
    this.location,
    this.result,
  });

  void setTestResult(TestResult result) {
    if (!this.submitted) {
      print("Changed to $result");
      this.result = result;
      notifyListeners();
    }
  }

  void submit() async {
    if (!submitted) {
      if (result != null) {
        submitted = true;
        notifyListeners();
        Map<String, String> requestHeaders = {
          'Content-type': 'application/json',
          'Authorization': '${auth.jsonWebToken}'
        };
        var value = await http.post(
          "$host/result/$id",
          headers: requestHeaders,
          body: jsonEncode(
            {"testResult": EnumToString.parse(result)},
          ),
        );
        print(value.statusCode);
        if (value.statusCode == 200) {
          
          dashboard.refresh();
          this.received = true;
          notifyListeners();
          await Future.delayed(Duration(seconds: 1));
          boards.changeBoard(Boards.DASHBOARD);
        } else {
          auth.logOut();
        }
      }
    }
  }
}
