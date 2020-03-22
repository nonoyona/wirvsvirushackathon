import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class BoardDropdown extends StatefulWidget {
  final String label;
  final TestResult initialValue;
  final void Function(TestResult result) onSubmitted;

  BoardDropdown({Key key, this.label, this.onSubmitted, this.initialValue})
      : super(key: key);

  @override
  _BoardDropdownState createState() => _BoardDropdownState();
}

class _BoardDropdownState extends State<BoardDropdown> {
  TestResult dropdownValue;

  @override
  void initState() {
    super.initState();
    dropdownValue = this.widget.initialValue;
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 5),
      child: Card(
        elevation: 5,
        color: Style.getSurfaceColor(5),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            SizedBox(
              width: 20,
            ),
            Text(
              widget.label ?? "NO LABEL!!! ARSCH",
              style: Style.body,
            ),
            Expanded(
              child: Container(
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: DropdownButton<TestResult>(
                  value: dropdownValue,
                  items: TestResult.values
                      .map(
                        (e) => DropdownMenuItem(
                          value: e,
                          child: Text(
                            EnumToString.parse(e),
                            style: Style.buttonText,
                          ),
                        ),
                      )
                      .toList(),
                  onChanged: (res) {
                    setState(() {
                      dropdownValue = res;
                      widget.onSubmitted(res);
                    });
                  },
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
