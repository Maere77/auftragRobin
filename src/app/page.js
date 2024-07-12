'use client'

import "./styleSheet.css";
import Link from "next/link";

export default function Home() {
    return (
        <div className={"container"}>
            <h1 className={"title"}>
                <span className={"titleFancy"}>Willkommen</span> Markus
            </h1>
            <nav className={"navi"}>
                <Link href={"./group"} className={"linke"}>Group</Link>
                <Link href={"./match"} className={"linke"}>Match</Link>
                <Link href={"./player"} className={"linke"}>Player</Link>
                <Link href={"./stadium"} className={"linke"}>Stadium</Link>
                <Link href={"./team"} className={"linke"}>Team</Link>
            </nav>
        </div>
    );
}