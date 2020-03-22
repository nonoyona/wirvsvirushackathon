import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:sick_or_not_admin/pages/login/login_logic.dart';
import 'package:sick_or_not_admin/pages/login/side_menu.dart';
import 'package:sick_or_not_admin/style.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Consumer<AuthNotifier>(
      builder: (context, notifier, child) {
        Future.microtask(() {
          if (notifier.loggedIn) {
            Navigator.pushReplacementNamed(context, "/board");
          }
        });
        return child;
      },
      child: ChangeNotifierProvider<LoginNotifier>(
        create: (context) => LoginNotifier(
            auth: Provider.of<AuthNotifier>(
          context,
          listen: false,
        )),
        child: Scaffold(
          backgroundColor: Style.surfaceColor,
          body: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              SideMenu(),
              Expanded(
                  child: Center(
                      child: Image.asset(
                "assets/login.png",
                height: 400,
              ))),
            ],
          ),
        ),
      ),
    );
  }
}
