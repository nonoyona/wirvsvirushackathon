import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/logic/auth_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/board_date_picker.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/board_input_field.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/create_case_logic.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class CaseCreationBoard extends StatelessWidget {
  const CaseCreationBoard({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<CreateCaseNotifier>(
      create: (context) => CreateCaseNotifier(
          Provider.of<AuthNotifier>(context, listen: false),
          Provider.of<DashboardNotifier>(context, listen: false)),
      child: Consumer<CreateCaseNotifier>(
        builder: (context, value, child) {
          return Container(
            padding: EdgeInsets.only(top: 20, right: 20, left: 20),
            child: ListView(
              children: <Widget>[
                Padding(
                    padding: const EdgeInsets.symmetric(vertical: 40),
                    child: Text(
                      "Add case",
                      style: Style.title,
                    )),
                BoardInputField(
                  label: "Location",
                  onSubmitted: value.setLocation,
                ),
                BoardDatePicker(
                  currentDate: value.date.toString(),
                  label: "Date",
                  onDatePicked: (date) {
                    value.setDate(date);
                  },
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  child: RaisedButton(
                    onPressed: () {
                      value.submit();
                    },
                    color: Style.primaryColor,
                    child: Text(
                      "SUBMIT",
                      style: Style.buttonText,
                    ),
                  ),
                ),
                buildResponse(context, value),
              ],
            ),
          );
        },
      ),
    );
  }

  Widget buildResponse(BuildContext context, CreateCaseNotifier notifier) {
    if (notifier.submitted) {
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
                    "Successfully added case with ID: ${notifier.id}",
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
          child: CircularProgressIndicator(),
        );
      }
    } else {
      return AnimatedSwitcher(
        duration: Duration(milliseconds: 500),
        child: Container(),
      );
    }
  }
}
