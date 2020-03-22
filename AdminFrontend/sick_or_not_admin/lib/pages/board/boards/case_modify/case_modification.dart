import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/case_modify/board_dropdown.dart';
import 'package:sick_or_not_admin/pages/board/boards/case_modify/board_text.dart';
import 'package:sick_or_not_admin/pages/board/boards/case_modify/case_modify_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/board_input_field.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';
import 'package:sick_or_not_admin/pages/board/logic/board_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class CaseModification extends StatefulWidget {
  static String id, location, date;
  static TestResult currentResult;

  const CaseModification({Key key}) : super(key: key);

  @override
  _CaseModificationState createState() => _CaseModificationState();
}

class _CaseModificationState extends State<CaseModification> {
  bool submitted = false;

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<CaseModificationNotifier>(
      create: (context) => CaseModificationNotifier(
        auth: Provider.of<AuthNotifier>(context, listen: false),
        boards: Provider.of<BoardNotifier>(context, listen: false),
        dashboard: Provider.of<DashboardNotifier>(context, listen: false),
        date: CaseModification.date,
        id: CaseModification.id,
        location: CaseModification.location,
        result: CaseModification.currentResult,
      ),
      child: Consumer<CaseModificationNotifier>(
          builder: (context, notifier, child) {
        return Container(
          padding: EdgeInsets.only(top: 20, right: 20, left: 20),
          child: ListView(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 40),
                child: Text(
                  "Case",
                  style: Style.title,
                ),
              ),
              Container(
                height: 100,
                child: Row(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      child: BoardText(
                        label: "ID",
                        value: notifier.id,
                      ),
                    ),
                    SizedBox(
                      width: 20,
                    ),
                    Expanded(
                      child: BoardText(
                        label: "Location",
                        value: notifier.location,
                      ),
                    ),
                    SizedBox(
                      width: 20,
                    ),
                    Expanded(
                      child: BoardText(
                        label: "Date",
                        value: notifier.date,
                      ),
                    ),
                  ],
                ),
              ),
              BoardDropdown(
                label: "Test result",
                initialValue: notifier.result,
                onSubmitted: notifier.setTestResult,
              ),
              Container(
                alignment: Alignment.centerLeft,
                child: RaisedButton(
                  onPressed: () {
                    notifier.submit();
                  },
                  color: Style.primaryColor,
                  child: Text(
                    "SUBMIT",
                    style: Style.buttonText,
                  ),
                ),
              ),
              buildResponse(context, notifier),
            ],
          ),
        );
      }),
    );
  }

  Widget buildResponse(BuildContext context, CaseModificationNotifier notifier) {
    if (notifier.received) {
      return AnimatedSwitcher(
        duration: Duration(milliseconds: 500),
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 5),
          child: Card(
            elevation: 5,
            color: Style.getSurfaceColor(5),
            child: Row(
              children: <Widget>[
                SizedBox(
                  width: 20,
                ),
                Icon(
                  Icons.check,
                  size: 48,
                  color: Style.primaryColor,
                ),
                SizedBox(
                  width: 20,
                ),
                Text(
                  "Successfully modified case!",
                  style: Style.body,
                ),
              ],
            ),
          ),
        ),
      );
    } else {
      return AnimatedSwitcher(
        duration: Duration(milliseconds: 500),
        child: Container(),
      );
    }
  }
}
