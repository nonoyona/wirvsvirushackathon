import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/pages/login/login_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class SideMenu extends StatefulWidget {
  const SideMenu({Key key}) : super(key: key);

  @override
  _SideMenuState createState() => _SideMenuState();
}

class _SideMenuState extends State<SideMenu> {
  @override
  Widget build(BuildContext context) {
    return Consumer<LoginNotifier>(builder: (context, notifier, child) {
      return Container(
        width: 300,
        child: Material(
          color: Style.getSurfaceColor(2),
          child: Column(
            children: <Widget>[
              Padding(
                padding: EdgeInsets.symmetric(vertical: 20),
                child: Text(
                  "Login",
                  style: Style.title,
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(10.0),
                child: Card(
                  elevation: 5,
                  color: Style.getSurfaceColor(5),
                  child: Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 10),
                    child: TextField(
                      style: Style.body,
                      onSubmitted: (value) {
                        notifier.setUsername(value);
                      },
                      decoration: InputDecoration(
                        labelText: "Username",
                        labelStyle: Style.body,
                      ),
                    ),
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(10.0),
                child: Card(
                  elevation: 5,
                  color: Style.getSurfaceColor(5),
                  child: Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 10),
                    child: TextField(
                      style: Style.body,
                      onSubmitted: (value) {
                        notifier.setPassword(value);
                      },
                      decoration: InputDecoration(
                        labelText: "Password",
                        labelStyle: Style.body,
                      ),
                    ),
                  ),
                ),
              ),
              RaisedButton(
                onPressed: !notifier.submitted
                    ? () {
                        notifier.submit();
                      }
                    : null,
                child: !notifier.submitted
                    ? Text(
                        "LOGIN",
                        style: Style.buttonText,
                      )
                    : (!notifier.failed
                        ? Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: CircularProgressIndicator(),
                          )
                        : Text(
                            "Login failed",
                            style: Style.buttonText,
                          )),
              )
            ],
          ),
        ),
      );
    });
  }
}
