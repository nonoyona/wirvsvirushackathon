import 'dart:convert';
import 'dart:html';

import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/SOSHost.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:http/http.dart' as http;

class DashboardNotifier extends ChangeNotifier {
  final AuthNotifier auth;

  List<CaseData> results = [];

  DashboardNotifier(this.auth) {
    Future.microtask(() async {
      var value = await http.post(
        "$host/cases",
        headers: {"Authorization": auth.jsonWebToken},
        body: jsonEncode({
          "caseCount": 200,
          "startIndex": 0,
        }),
      );

      if (value.statusCode == HttpStatus.accepted) {
        var objects = (jsonDecode(value.body)["cases"] as List);
        this.results = objects.map((e) => CaseData.fromMap(e)).toList();
        notifyListeners();
      } else {
        auth.logOut();
      }
    });
  }

  List<CaseData> getResults() {
    return results;
  }
}

class CaseData {
  final String id;
  final String username;
  final String location;
  final int date;
  final TestResult health;
  CaseData({
    this.id,
    this.username,
    this.location,
    this.date,
    this.health,
  });

  CaseData copyWith({
    String id,
    String username,
    String location,
    int date,
    TestResult health,
  }) {
    return CaseData(
      id: id ?? this.id,
      username: username ?? this.username,
      location: location ?? this.location,
      date: date ?? this.date,
      health: health ?? this.health,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'username': username,
      'location': location,
      'date': date,
      'health': health.toString(),
    };
  }

  static CaseData fromMap(Map<String, dynamic> map) {
    if (map == null) return null;

    return CaseData(
      id: map['id'],
      username: map['username'],
      location: map['location'],
      date: map['date'],
      health: TestResult.values[map['health']],
    );
  }

  String toJson() => json.encode(toMap());

  static CaseData fromJson(String source) => fromMap(json.decode(source));

  @override
  String toString() {
    return 'CaseData(id: $id, username: $username, location: $location, date: $date, health: $health)';
  }

  @override
  bool operator ==(Object o) {
    if (identical(this, o)) return true;

    return o is CaseData &&
        o.id == id &&
        o.username == username &&
        o.location == location &&
        o.date == date &&
        o.health == health;
  }

  @override
  int get hashCode {
    return id.hashCode ^
        username.hashCode ^
        location.hashCode ^
        date.hashCode ^
        health.hashCode;
  }
}

enum TestResult { UNTESTED, NEGATIVE, POSITIVE }
