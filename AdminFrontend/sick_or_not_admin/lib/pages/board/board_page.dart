import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/case_creation.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dash_board.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';
import 'package:sick_or_not_admin/pages/board/logic/board_logic.dart';
import 'package:sick_or_not_admin/pages/board/side_menu.dart';
import 'package:sick_or_not_admin/style.dart';

class BoardPage extends StatelessWidget {
  const BoardPage({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<BoardNotifier>(
      create: (context) => BoardNotifier(),
      child: ChangeNotifierProvider<DashboardNotifier>(
        create: (context) => DashboardNotifier(Provider.of<AuthNotifier>(context, listen: false)),
        child: Scaffold(
          backgroundColor: Style.surfaceColor,
          body: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              SideMenu(),
              Expanded(
                child:
                    Consumer<BoardNotifier>(builder: (context, value, child) {
                  switch (value.currentBoard) {
                    case Boards.DASHBOARD:
                      return Dashboard();
                    case Boards.CREATE_CASE:
                      return CaseCreationBoard();
                    default:
                      return Center(
                          child: Text(
                        "Error 404",
                        style: Style.title,
                      ));
                  }
                }),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
