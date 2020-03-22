import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/style.dart';

class BoardText extends StatelessWidget {
  final String label, value;

  const BoardText({Key key, this.label, this.value}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 5),
      child: Card(
        elevation: 5,
        color: Style.getSurfaceColor(5),
        child: Container(
          height: 50,
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
              Container(
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: Text(
                  this.value ?? "No value",
                  style: Style.body,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
