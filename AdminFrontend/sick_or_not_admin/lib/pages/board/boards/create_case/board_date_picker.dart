import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/style.dart';

class BoardDatePicker extends StatelessWidget {
  final String label;
  final String currentDate;
  final void Function(DateTime date) onDatePicked;

  const BoardDatePicker(
      {Key key, this.label, @required this.onDatePicked, @required this.currentDate})
      : super(key: key);

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
              label ?? "NO LABEL!!! ARSCH",
              style: Style.body,
            ),
            Expanded(
              child: Container(
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: FlatButton(
                  onPressed: () {
                    showDatePicker(
                      context: context,
                      initialDate: DateTime.now(),
                      firstDate: DateTime.fromMillisecondsSinceEpoch(0),
                      lastDate: DateTime.now(),
                      initialDatePickerMode: DatePickerMode.day,
                    ).then((value) => this.onDatePicked(value));
                  },
                  child: Text(
                    currentDate,
                    style: Style.buttonText,
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
