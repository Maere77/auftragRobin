'use client'

import {useEffect, useState} from "react";
import styles from "/src/app/page.module.css";
import Link from "next/link";

export default function Player() {
    const [group, setGroup] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const playersPerPage = 30;

    useEffect(() => {
        fetch("http://localhost:8080/api/stadiums")
            .then((response) => response.json())
            .then((data) => {
                setGroup(data);
            })
            .catch((error) => console.error("Fehler: ", error));
    }, []);

    const indexOfLastPlayer = currentPage * playersPerPage;
    const indexOfFirstPlayer = indexOfLastPlayer - playersPerPage;
    const currentGroup = group.slice(indexOfFirstPlayer, indexOfLastPlayer);

    const totalPages = Math.ceil(group.length / playersPerPage);

    return (
        <div className={styles.container}>
            <Link href={"./"}>Home page</Link>
            <table className={styles.table}>
                <thead>
                <tr className={styles.headerRow}>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Place</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {currentGroup.map((group, index) => (
                    <tr key={group.id} className={index % 2 === 0 ? styles.evenRow : styles.oddRow}>
                        <td>{group.id}</td>
                        <td>{group.stadiumName}</td>
                        <td>{group.place}</td>
                        <td>
                            <a href={`./player/edit/${group.id}`}>EDIT</a>
                        </td>
                        <td>
                            <a className={"ef"} href={`./player/edit/${group.id}`}>DELETE</a>
                        </td>
                        <td>
                            <a className={"ef"} href={`./player/edit/${group.id}`}>SHOW</a>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div className={styles.pagination}>
                <button
                    onClick={() => setCurrentPage(currentPage - 1)}
                    disabled={currentPage === 1}
                >
                    Previous
                </button>
                {Array.from({length: totalPages}, (_, i) => (
                    <button
                        key={i + 1}
                        onClick={() => setCurrentPage(i + 1)}
                        className={currentPage === i + 1 ? styles.activePage : ''}
                    >
                        {i + 1}
                    </button>
                ))}
                <button
                    onClick={() => setCurrentPage(currentPage + 1)}
                    disabled={currentPage === totalPages}
                >
                    Next
                </button>
            </div>
        </div>
    );
}
