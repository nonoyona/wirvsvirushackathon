import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/case_counter.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/case_list.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';

class Dashboard extends StatelessWidget {
  const Dashboard({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.only(top: 20, right: 20, left: 20),
      child: ListView(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 40),
            child: CaseCounters(),
          ),
          SizedBox(
            height: 20,
          ),
          CaseListItem(
            id: "ID",
            date: "Date",
            location: "Location",
            state: "Test result",
          ),
          SizedBox(
            height: 10,
          ),
        ]..addAll(getCases(context)),
      ),
    );
  }

  List<Widget> getCases(BuildContext context) {

    var result = Provider.of<DashboardNotifier>(context).getResults();

    return result
        .map(
          (e) => CaseListItem(
            id: e.id,
            date: DateTime.fromMillisecondsSinceEpoch(e.date).toIso8601String(),
            location: e.location,
            state: e.health.toString(),
          ),
        )
        .toList();
  }
}
