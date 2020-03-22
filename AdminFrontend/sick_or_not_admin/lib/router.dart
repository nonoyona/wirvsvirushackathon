import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/main.dart';
import 'package:sick_or_not_admin/pages/board/board_page.dart';
import 'package:sick_or_not_admin/pages/login/login_page.dart';

class Router {
  Route<dynamic> generateRoute(RouteSettings settings) {
    switch (settings.name) {
      case '/board':
        return MaterialPageRoute(builder: (_) => BoardPage());
      case '/login':
        return MaterialPageRoute(builder: (_) => LoginPage());
    }
  }
}
