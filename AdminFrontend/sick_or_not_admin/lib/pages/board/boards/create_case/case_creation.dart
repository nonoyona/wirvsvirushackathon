import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/board_date_picker.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/board_input_field.dart';
import 'package:sick_or_not_admin/pages/board/boards/create_case/create_case_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class CaseCreationBoard extends StatelessWidget {
  const CaseCreationBoard({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<CreateCaseNotifier>(
      create: (context) => CreateCaseNotifier(),
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
                ),
                BoardDatePicker(
                  currentDate: value.date.toString(),
                  label: "Date",
                  onDatePicked: (date) {},
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  child: RaisedButton(
                    onPressed: () {},
                    color: Style.primaryColor,
                    child: Text(
                      "SUBMIT",
                      style: Style.buttonText,
                    ),
                  ),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
