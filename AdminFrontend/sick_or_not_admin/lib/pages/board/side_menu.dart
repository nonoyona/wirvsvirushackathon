import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/pages/board/logic/board_logic.dart';
import 'package:sick_or_not_admin/pages/board/side_menu_item.dart';
import 'package:sick_or_not_admin/style.dart';

class SideMenu extends StatelessWidget {
  const SideMenu({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,
      child: Material(
        color: Style.getSurfaceColor(2),
        child: Column(
          children: <Widget>[
            Padding(
                padding: EdgeInsets.symmetric(vertical: 20, horizontal: 20),
                child: Image.asset("assets/logo.png")),
            SideMenuItem(
              title: "DASHBOARD",
              board: Boards.DASHBOARD,
            ),
            SideMenuItem(
              title: "ADD CASE",
              board: Boards.CREATE_CASE,
            )
          ],
        ),
      ),
    );
  }
}
