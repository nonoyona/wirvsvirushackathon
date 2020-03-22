import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/style.dart';

class BoardInputField extends StatelessWidget {
  final void Function(String str) onSubmitted;
  final String label;

  const BoardInputField({Key key, this.onSubmitted, this.label})
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
                child: TextField(
                  style: Style.body,
                  onSubmitted: this.onSubmitted,
                  decoration: InputDecoration(
                      hintText: "put your text here",
                      hintStyle: Style.body.copyWith(color: Colors.white54)),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
